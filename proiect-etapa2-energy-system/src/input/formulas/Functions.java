package input.formulas;

import data.Distributors;
import data.Consumers;
import data.Contract;
import data.ContractFactory;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * contine metodele utilizate pentru a simula actiuni
 */
public final class Functions {

    static final double CONSTANT = 1.2;

    /**
     * metoda va parcurge lista de distribuitori si va actualiza
     * valoarea contractului si profitul obtinut dupa schimbari
     */
    public void setData(final ArrayList<Distributors> distributors) {
        int i;

        // se va alege clasa cu formule in functie de numarul de consumatori
        // ai fiecarui distribuitor, utilizant design pattern-ul Strategy
        GetFormulaStrategy strategy = GetFormulaStrategy.getInstance();
        Formula formula;

        for (i = 0; i < distributors.size(); i++) {
            if (distributors.get(i).getNumberOfConsumers() == 0) {
                formula = strategy.getFormula(GetFormulaStrategy.StrategyType.withoutConsumers);
            } else {
                formula = strategy.getFormula(GetFormulaStrategy.StrategyType.withConsumers);
            }

            distributors.get(i).setProfit(formula.getProfit(distributors.
                    get(i).getProductionCost()));

            distributors.get(i).setPrice(formula.finalPrice(distributors.
                            get(i).getInfrastructureCost(),
                    distributors.get(i).getProductionCost(),
                    distributors.get(i).getProfit(), distributors.get(i).getNumberOfConsumers()));
        }
    }

    /**
     * metoda va parcurge lista de distribuitori si returneaza
     * id-ul distribuitorului ce ofera pretul contractului minim
     */
    public int getMinimDistributorId(final ArrayList<Distributors> distributors) {
        int i, minim, id;
        id = 0;
        minim = 0;

        // se cauta primul distribuitor nefalimentar si se fixeaza pretul sau ca fiind minim
        // ulterior se compara cu restul distribuitorilor
        for (i = 0; i < distributors.size(); i++) {
            if (distributors.get(i).getPrice() != 0 && distributors.get(i).getBankrupt() == 0) {
                minim = distributors.get(i).getPrice();
                id = i;
                break;
            }
        }

        for (i = 0; i < distributors.size(); i++) {
            if (distributors.get(i).getPrice() < minim && distributors.get(i).getBankrupt() == 0) {
                minim = distributors.get(i).getPrice();
                id = i;
            }
        }

        return id;
    }


    /**
     * metoda va parcurge lista de distribuitori si va realiza un contract nou intre
     * consumatorul dat ca parametru si distribuitorul cu pretul minim
     */

    public void makeContract(final ArrayList<Distributors> distributors, final Consumers consumer) {
        int id = getMinimDistributorId(distributors);
        int i, nrConsumers;

        for (i = 0; i < distributors.size() && consumer.getBankrupt() != 2; i++) {
            if (distributors.get(i).getId() == id) {
                HashMap<Integer, Contract> contracts = distributors.get(i).getContracts();
                ArrayList<Integer> idList = distributors.get(i).getIdConsumers();

                if (contracts == null && idList == null) {
                    contracts = new HashMap<>();
                    idList = new ArrayList<>();
                }

                // se adauga un nou contract in lista de contracte a distribuitorului
                // utilizeaza factory pentru a obtine un obiect tip Contract
                ContractFactory contractFactory = new ContractFactory();
                Contract contract = (Contract) contractFactory.
                        getContract(ContractFactory.ContractType.normalContract, consumer.getId(),
                                distributors.get(i).getPrice(),
                                distributors.get(i).getContractLength());

                idList.add(consumer.getId());
                distributors.get(i).setIdConsumers(idList);

                assert contracts != null;
                contracts.put(consumer.getId(), contract);
                distributors.get(i).setContracts(contracts);

                // se seteaza pretul constractului pe care il va plati lunar
                // consumatorul, durata lui si id-ul distribuitorului sau
                consumer.setIdDistributor(id);
                consumer.setPrice(distributors.get(i).getPrice());
                consumer.setDurationofContract(distributors.get(i).getContractLength());

                // se creste numarul de consumatori ai distribuitorului sau
                nrConsumers = distributors.get(i).getNumberOfConsumers();
                nrConsumers++;
                distributors.get(i).setNumberOfConsumers(nrConsumers);
            }
        }
    }

    /**
     * metoda va parcurge lista de consumatori si va adauga la bugetul
     * fiecaruia venitul sau lunar
     */
    public void paySalaryConsumers(final ArrayList<Consumers> consumers) {
        int i;
        int budget;
        for (i = 0; i < consumers.size(); i++) {
            if (consumers.get(i).getBankrupt() != 2) {
                budget = consumers.get(i).getBudget() + consumers.get(i).getMonthlyIncome();
                consumers.get(i).setInitialBudget(budget);
                consumers.get(i).setBudget(budget);
            }
        }
    }

    /**
     * metoda va parcurge lista de distribuitori si il returneaza pe cel caruia ii apartine
     * pretul minim al contractului
     */
    public Distributors getDistributor(final ArrayList<Distributors> distributors, final int id) {
        int i;
        for (i = 0; i < distributors.size(); i++) {
            if (distributors.get(i).getId() == id) {
                return distributors.get(i);
            }
        }
        return null;
    }

    /**
     * metoda va gasi distribuitorul dorit si ii va scadea o luna din
     * durata contractului cu acel consumator
     */
    public void reduceNrMonths(final ArrayList<Distributors> distributors,
                               final Consumers consumer, final int id) {
        int lenght;
        Distributors distributor = getDistributor(distributors, id);

        lenght = consumer.getDurationofContract();
        lenght--;
        consumer.setDurationofContract(lenght);

        assert distributor != null;
        HashMap<Integer, Contract> contracts = distributor.getContracts();
        Contract contract = contracts.get(consumer.getId());

        if (contract != null) {
            contract.setRemainedMonths(lenght);
            contracts.replace(consumer.getId(), contract);
        }

        distributor.setContracts(contracts);
    }

    /**
     * metoda va obtine distribuitorul dorit si ii va adauga la buget
     * valoarea contractului platit de consumator lunar
     */

    public void payDistribuitor(final ArrayList<Distributors> distributors,
                                final Consumers consumer, final int id) {
        int budget;
        Distributors distributor = getDistributor(distributors, id);
        if (distributor != null) {
            budget = distributor.getBudget() + consumer.getPrice();
            distributor.setBudget(budget);
        }
        reduceNrMonths(distributors, consumer, id);
    }

    /**
     * metoda va parcurge lista de consumatori si daca au bani pentru a plati
     * contractul se va scade suma respectiva din bugetul lor si va fi
     * adaugata in cel al distribuitorului
     * daca nu pot plati prima data se retine penalizarea si sunt marcati
     * pentru consumatorii restanti se va verifica daca pot plati penalizarea
     * in caz contrar sunt declarati falimentari
     */
    public void payConsumers(final ArrayList<Distributors> distributors,
                             final ArrayList<Consumers> consumers) {
        int i;
        int budget, penalization;

        for (i = 0; i < consumers.size(); i++) {
            budget = consumers.get(i).getBudget() - consumers.get(i).getPrice();

            if (budget >= 0 && consumers.get(i).getBankrupt() == 0) {
                consumers.get(i).setBudget(budget);
                payDistribuitor(distributors, consumers.get(i),
                        consumers.get(i).getIdDistributor());
                consumers.get(i).setInitialBudget(budget);

            } else {
                if (consumers.get(i).getBankrupt() == 0) {
                    consumers.get(i).setBankrupt(1);
                    penalization = (int) Math.round(Math.floor(CONSTANT
                            * consumers.get(i).getPrice()));

                    consumers.get(i).setPenalization(penalization);
                    reduceNrMonths(distributors, consumers.get(i),
                            consumers.get(i).getIdDistributor());

                    if (consumers.get(i).getDurationofContract() == 0) {
                        consumers.get(i).setNewDistributors(1);
                    } else {
                        consumers.get(i).setNewDistributors(0);
                    }

                } else if (consumers.get(i).getBankrupt() == 1) {

                    penalization = consumers.get(i).getPenalization()
                            + consumers.get(i).getPrice();

                    if (penalization > consumers.get(i).getBudget()
                            && consumers.get(i).getNewDistributors() == 0) {
                        consumers.get(i).setBankrupt(2);

                    } else {
                        if (consumers.get(i).getNewDistributors() == 0) {
                            budget = consumers.get(i).getBudget() - penalization;
                            consumers.get(i).setBudget(budget);
                            consumers.get(i).setInitialBudget(budget);
                            consumers.get(i).setBankrupt(0);
                        } else {
                            budget = consumers.get(i).getBudget() - penalization
                                    + consumers.get(i).getPrice();
                            consumers.get(i).setBudget(budget);
                            consumers.get(i).setInitialBudget(budget);
                            consumers.get(i).setBankrupt(0);
                        }
                    }
                }
            }

        }
    }

    /**
     * metoda va parcurge lista de distribuitori si va scadea din bugetul fiecaruia
     * cheltuielile lunare
     */
    public void budgetDistributor(final ArrayList<Distributors> distributors) {
        int i;
        int budget, costs;

        for (i = 0; i < distributors.size(); i++) {
            costs = distributors.get(i).getInfrastructureCost()
                    + distributors.get(i).getProductionCost()
                    * distributors.get(i).getNumberOfConsumers();

            if (distributors.get(i).getBankrupt() == 0) {
                budget = distributors.get(i).getBudget() - costs;
                distributors.get(i).setBudget(budget);
            }
        }
    }


    /**
     * metoda va parcurge lista de consumatori si va sterge contractele care
     * s-au terminat, reducand simultan numarul de consumatori ai distribuitorului
     */
    public void releaseContract(final ArrayList<Distributors> distributors,
                                final ArrayList<Consumers> consumers) {
        int i;
        for (i = 0; i < consumers.size(); i++) {
            if (consumers.get(i).getDurationofContract() == 0) {
                for (Distributors distributor : distributors) {
                    if (distributor.getId() == consumers.get(i).getIdDistributor()) {
                        HashMap<Integer, Contract> contracts = distributor.getContracts();
                        ArrayList<Integer> idList = distributor.getIdConsumers();

                        if (contracts != null && contracts.containsKey(consumers.get(i).getId())) {
                            idList.remove(Integer.valueOf(consumers.get(i).getId()));
                            int nrConsumers = distributor.getNumberOfConsumers();
                            nrConsumers--;
                            distributor.setNumberOfConsumers(nrConsumers);

                            contracts.remove(consumers.get(i).getId());
                            distributor.setContracts(contracts);
                        }
                    }
                }
            }
        }
    }

    /**
     * metoda va parcurge lista de consumatori si ii va sterge pe cei care
     * au dat faliment reducand simultan numarul de consumatori ai distribuitorului
     */
    public void deleteCosumator(final ArrayList<Distributors> distributors,
                                final ArrayList<Consumers> consumers) {
        int i;
        for (i = 0; i < consumers.size(); i++) {
            if (consumers.get(i).getBankrupt() == 2) {
                for (Distributors distributor : distributors) {
                    if (distributor.getId() == consumers.get(i).getIdDistributor()) {
                        HashMap<Integer, Contract> contracts = distributor.getContracts();
                        if (contracts != null && contracts.containsKey(consumers.get(i).getId())) {
                            ArrayList<Integer> idList = distributor.getIdConsumers();
                            idList.remove(Integer.valueOf(consumers.get(i).getId()));
                            int nrConsumers = distributor.getNumberOfConsumers();
                            nrConsumers--;

                            distributor.setNumberOfConsumers(nrConsumers);
                            contracts.remove(consumers.get(i).getId());
                            distributor.setContracts(contracts);
                        }
                    }
                }
            }
        }
    }

    /**
     * metoda va parcurge lista de consumatori si daca acestia nu au contract
     * va apela metoda pentru a le gasi o sursa de electricitate
     */
    public void checkContracts(final ArrayList<Distributors> distributors,
                               final ArrayList<Consumers> consumers) {
        int i;
        for (i = 0; i < consumers.size(); i++) {
            if (consumers.get(i).getDurationofContract() == 0
                    && consumers.get(i).getBankrupt() != 2) {
                makeContract(distributors, consumers.get(i));
            }
        }
    }

    /**
     * metoda va parcurge lista de distribuitori si pentru fiecare distribuitor falimentar
     * va elibera contractele clientilor sai
     */
    public void distributorIsBankrupt(final ArrayList<Distributors> distributors,
                                      final ArrayList<Consumers> consumers) {

        int i;
        for (i = 0; i < distributors.size(); i++) {
            if (distributors.get(i).getBudget() < 0 && distributors.get(i).getBankrupt() == 0) {
                distributors.get(i).setBankrupt(1);

                for (Consumers consumer : consumers) {
                    if (consumer.getIdDistributor() == distributors.get(i).getId()) {
                        HashMap<Integer, Contract> contracts = distributors.get(i).getContracts();

                        if (contracts != null && contracts.containsKey(consumer.getId())) {
                            int nrConsumers = distributors.get(i).getNumberOfConsumers();
                            nrConsumers--;
                            distributors.get(i).setNumberOfConsumers(nrConsumers);
                            contracts.remove(consumer.getId());
                            distributors.get(i).setContracts(contracts);
                        }
                    }
                }
            }
        }
    }
}
