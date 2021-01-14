package simulation;

import actions.CostsChanges;
import actions.MonthlyUpdates;
import actions.NewConsumers;

import data.Consumers;
import data.OutputContract;
import data.ContractFactory;
import data.Distributors;
import data.Contract;

import input.Input;
import input.initialData.InputConsumers;
import input.initialData.InputDistributors;
import input.formulas.Functions;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import output.Output;
import output.OutputConsumers;
import output.OutputDistributors;


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
                    inputDistributors.get(i).getInitialProductionCost(),
                    inputDistributors.get(i).getInitialBudget(), 0,
                    inputDistributors.get(i).getInitialProductionCost(),
                    inputDistributors.get(i).getInitialInfrastructureCost(), 0, 0, 0);
            distributors.add(distributor);
        }

        simulations(input, consumers, distributors);

        return writeOutput(consumers, distributors);

    }

    /**
     * metoda va simula runda initiala
     */
    public void firstRound(final ArrayList<Consumers> consumers,
                           final ArrayList<Distributors> distributors) {

        Functions function = new Functions();
        function.setData(distributors);

        for (Consumers consumer : consumers) {
            function.makeContract(distributors, consumer);
        }

        function.paySalaryConsumers(consumers);
        function.payConsumers(distributors, consumers);
        function.budgetDistributor(distributors);

        function.print(distributors, consumers);

    }

    /**
     * metoda va simula fiecare runda
     */
    public void simulations(final Input input, final ArrayList<Consumers> consumers,
                            final ArrayList<Distributors> distributors) {

        int j;
        Functions function = new Functions();
        List<MonthlyUpdates> updates = input.getMonthlyUpdates();
        firstRound(consumers, distributors);

        for (int i = 0; i < input.getNumberOfTurns(); i++) {
            // se recalculeaza datele distribuitorilor
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

                // se modifica costul infrastructurii si al productiei unor distributori
                List<CostsChanges> changes = updates.get(i).getCostsChanges();
                for (j = 0; j < changes.size(); j++) {
                    CostsChanges change = changes.get(j);
                    for (Distributors distributor : distributors) {
                        if (distributor.getId() == change.getId()) {
                            distributor.setInfrastructureCost(change.
                                    getInfrastructureCost());
                            distributor.setProductionCost(change.getProductionCost());
                        }
                    }
                }

                // se recalculeaza pretul contractului fiecarui distributor
                if (!changes.isEmpty()) {
                    function.setData(distributors);
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


            System.out.print("\n Round " + i + "\n");
            function.print(distributors, consumers);

        }
    }


    /**
     * metoda va popula cu datele obtinute in urma jocului
     * clasele pentru scrierea in fisier
     */
    public Output writeOutput(final ArrayList<Consumers> consumers,
                              final ArrayList<Distributors> distributors) {

        boolean bankrupt;
        int i;
        ArrayList<OutputConsumers> outputConsumers = new ArrayList<>();
        ArrayList<OutputDistributors> outputDistributors = new ArrayList<>();

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
                    get(i).getId(), distributors.get(i).getBudget(), bankrupt,
                    outContracts);
            outputDistributors.add(outputDistributors1);
        }

        return new Output(outputConsumers, outputDistributors);
    }

}
