package simulation;

import actions.DistributorChanges;
import actions.MonthlyUpdates;
import actions.NewConsumers;

import actions.ProducerChanges;
import data.Consumers;
import data.Producers;
import data.Distributors;
import data.Contract;
import data.ContractFactory;
import data.OutputContract;
import data.MonthlyStats;


import data.observer.Changes;
import input.Input;
import input.formulas.FunctionsChangesProducers;
import input.initialData.InputConsumers;
import input.initialData.InputDistributors;
import input.formulas.Functions;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import input.initialData.InputProducers;
import output.Output;
import output.OutputMonthlyStats;
import output.OutputProducers;
import output.OutputDistributors;
import output.OutputConsumers;


public final class Simulations {

    /**
     * metoda va construi noi liste de consumatori si distribuitori
     * obtinute prin copierea datelor de input
     * va apela metodele pentru a simula jocul
     * va returna clasa ce contine output-ul
     */
    public Output start(final Input input) {
        int i;

        ArrayList<Consumers> consumers = new ArrayList<>();
        List<InputConsumers> inputConsumers = input.getInitialData().getConsumers();

        for (i = 0; i < inputConsumers.size(); i++) {
            Consumers consumer = new Consumers(inputConsumers.get(i).getId(),
                    inputConsumers.get(i).getInitialBudget(),
                    inputConsumers.get(i).getMonthlyIncome(),
                    inputConsumers.get(i).getInitialBudget(),
                    0, 0);
            consumers.add(consumer);
        }

        ArrayList<Distributors> distributors = new ArrayList<>();
        List<InputDistributors> inputDistributors = input.getInitialData().getDistributors();

        for (i = 0; i < inputDistributors.size(); i++) {
            Distributors distributor = new Distributors(inputDistributors.get(i).getId(),
                    inputDistributors.get(i).getContractLength(),
                    inputDistributors.get(i).getInitialBudget(),
                    inputDistributors.get(i).getInitialInfrastructureCost(),
                    inputDistributors.get(i).getEnergyNeededKW(),
                    inputDistributors.get(i).getProducerStrategy(), 0,
                    inputDistributors.get(i).getInitialBudget(), 0, 0,
                    inputDistributors.get(i).getInitialInfrastructureCost(), 0, 0, 0, 0);
            distributors.add(distributor);
        }

        ArrayList<Producers> producers = new ArrayList<>();
        List<InputProducers> inputProducers = input.getInitialData().getProducers();
        for (i = 0; i < inputProducers.size(); i++) {
            Producers producer = new Producers(inputProducers.get(i).getId(),
                    inputProducers.get(i).getEnergyType(),
                    inputProducers.get(i).getMaxDistributors(),
                    inputProducers.get(i).getPriceKW(),
                    inputProducers.get(i).getEnergyPerDistributor(), 0);
            producers.add(producer);
        }


        simulations(input, consumers, distributors, producers);

        return writeOutput(consumers, distributors, producers);

    }

    /**
     * metoda va simula runda initiala
     */
    public void firstRound(final ArrayList<Consumers> consumers,
                           final ArrayList<Distributors> distributors,
                           final ArrayList<Producers> producers) {

        Functions function = new Functions();
        FunctionsChangesProducers changesProducers = new FunctionsChangesProducers();
        changesProducers.chooseInitialProducers(distributors, producers, 0);

        function.setData(distributors);

        for (Consumers consumer : consumers) {
            function.makeContract(distributors, consumer);
        }

        function.paySalaryConsumers(consumers);
        function.payConsumers(distributors, consumers);
        function.budgetDistributor(distributors);

    }

    /**
     * metoda va simula fiecare runda
     */
    public void simulations(final Input input, final ArrayList<Consumers> consumers,
                            final ArrayList<Distributors> distributors,
                            final ArrayList<Producers> producers) {

        int j;
        Functions function = new Functions();
        List<MonthlyUpdates> updates = input.getMonthlyUpdates();
        FunctionsChangesProducers changesProducers = new FunctionsChangesProducers();

        // fiecare distribuitor isi alege producatorii
        changesProducers.setMonthlyStats(input.getNumberOfTurns(), producers);

        // se simuleaza prima runda
        firstRound(consumers, distributors, producers);

        // se creaza un obiect observabil
        Changes subject = new Changes();

        // se asigneaza distribuitorii ca observatori ai clasei Changes
        for (j = 0; j < distributors.size(); j++) {
            subject.attach(distributors.get(j));
        }

        for (int i = 0; i < input.getNumberOfTurns(); i++) {
            // se recalculeaza datele distribuitorilor

            //changesProducers.chooseInitialProducers(distributors, producers, i+1);
            function.setData(distributors);

            // se parcurge lista de schimbari lunare
            // se adauga noi consumatori
            if (updates.get(i) != null) {
                List<NewConsumers> newConsumers = updates.get(i).getNewConsumers();
                if (!newConsumers.isEmpty()) {
                    for (j = 0; j < newConsumers.size(); j++) {
                        Consumers consumer = new Consumers(newConsumers.get(j).getId(),
                                newConsumers.get(j).getInitialBudget(),
                                newConsumers.get(j).getMonthlyIncome(),
                                newConsumers.get(j).getInitialBudget(), 0, 0);
                        consumers.add(consumer);
                    }
                }

                // se modifica costul infrastructurii unor distributori
                List<DistributorChanges> changes = updates.get(i).getDistributorChanges();
                for (j = 0; j < changes.size(); j++) {
                    DistributorChanges change = changes.get(j);
                    for (Distributors distributor : distributors) {
                        if (distributor.getId() == change.getId()) {
                            distributor.setInfrastructureCost(change.
                                    getInfrastructureCost());
                        }
                    }
                }

                // se recalculeaza pretul contractului fiecarui distributor
                if (!changes.isEmpty()) {
                    function.setData(distributors);
                }

                // se retin schimbarile aduse producatorilor
                List<ProducerChanges> changesProducer = updates.get(i).getProducerChanges();
                for (j = 0; j < changesProducer.size(); j++) {
                    ProducerChanges change1 = changesProducer.get(j);
                    for (Producers producers1 : producers) {
                        if (producers1.getId() == change1.getId()) {
                            producers1.setEnergyPerDistributor(change1.getEnergyPerDistributor());
                        }
                    }

                    // se anunta observatorii de schimbarile produse
                    subject.notifyUpdate(change1);
                }

            }

            // se elibereaza contractele care au expirat
            function.releaseContract(distributors, consumers);
            // se fac noi contracte pentru consumatorii fara sursa de electricitate
            function.checkContracts(distributors, consumers);
            // se adauga venitul lunar in bugetul consumatorilor
            function.paySalaryConsumers(consumers);
            // consumatorii vor plati rata lunara
            function.payConsumers(distributors, consumers);
            // se recalculeaza bugetul distribuitorilor
            function.budgetDistributor(distributors);
            // se sterg contractele consumatorilor ajunsi la faliment
            function.deleteCosumator(distributors, consumers);
            // se sterg contractele distribuitorilor ajunsi la faliment
            function.distributorIsBankrupt(distributors, consumers);
            // distribuitorii ramasi fara producatori isi vor alege altii
            changesProducers.chooseInitialProducers(distributors, producers, i + 1);

        }
    }


    /**
     * metoda va popula cu datele obtinute in urma jocului
     * clasele pentru scrierea in fisier
     */
    public Output writeOutput(final ArrayList<Consumers> consumers,
                              final ArrayList<Distributors> distributors,
                              final ArrayList<Producers> producers) {

        boolean bankrupt;
        int i;
        ArrayList<OutputConsumers> outputConsumers = new ArrayList<>();
        ArrayList<OutputDistributors> outputDistributors = new ArrayList<>();
        ArrayList<OutputProducers> outputProducers = new ArrayList<>();
        // copiaza consumatorii
        for (i = 0; i < consumers.size(); i++) {
            bankrupt = consumers.get(i).getBankrupt() == 2;
            OutputConsumers outputConsumer1 = new OutputConsumers(consumers.get(i).getId(),
                    bankrupt, consumers.get(i).getBudget());
            outputConsumers.add(outputConsumer1);
        }

        // copiaza datele despre distribuitori
        for (i = 0; i < distributors.size(); i++) {
            bankrupt = distributors.get(i).getBudget() <= 0;
            ArrayList<OutputContract> outContracts = new ArrayList<>();

            if (distributors.get(i).getContracts() != null) {
                HashMap<Integer, Contract> contracts = new HashMap<>(distributors.get(i).
                        getContracts());

                ArrayList<Integer> idList = distributors.get(i).getIdConsumers();
                ContractFactory contractFactory = new ContractFactory();

                for (Integer integer : idList) {
                    Contract contract = contracts.get(integer);
                    // utilizeaza factory pentru a obtine un obiect tip OutputContract
                    OutputContract contractOutput = (OutputContract) contractFactory.
                            getContract(ContractFactory.ContractType.outputContract,
                                    contract.getId(), contract.getPrice(),
                                    contract.getRemainedMonths());

                    outContracts.add(contractOutput);
                }
            }

            OutputDistributors outputDistributors1 = new OutputDistributors(distributors.
                    get(i).getId(), distributors.get(i).getEnergyNeededkW(),
                    distributors.get(i).getPrice(), distributors.get(i).getBudget(),
                    distributors.get(i).getProducerStrategy(), bankrupt,
                    outContracts);
            outputDistributors.add(outputDistributors1);
        }

        // copiaza datele despre producatori
        for (i = 0; i < producers.size(); i++) {

            OutputProducers outputProducer = new OutputProducers(producers.get(i).getId(),
                    producers.get(i).getEnergyType(), producers.get(i).getMaxDistributors(),
                    producers.get(i).getPriceKW(), producers.get(i).getEnergyPerDistributor(),
                    null);

            ArrayList<MonthlyStats> monthlyStats = producers.get(i).getMonthlyStats();
            ArrayList<OutputMonthlyStats> outputMonthlyStats = new ArrayList<>();

            if (monthlyStats != null) {
                for (int j = 1; j < monthlyStats.size(); j++) {
                    OutputMonthlyStats stat = new OutputMonthlyStats(monthlyStats.get(j).getMonth(),
                            monthlyStats.get(j).getDistributorsIds());
                    outputMonthlyStats.add(stat);
                }

                outputProducer.setMonthlyStats(outputMonthlyStats);
            }
            outputProducers.add(outputProducer);
        }

        return new Output(outputConsumers, outputDistributors, outputProducers);
    }

}
