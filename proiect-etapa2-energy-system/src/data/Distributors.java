package data;

import actions.ProducerChanges;
import data.observer.Observer;
import input.formulas.strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * informatia despre un distribuitor
 */
public final class Distributors implements Observer {

    /**
     * id-ul sau
     */
    private int id;

    /**
     * durata contractului pe care o ofera consumatorilor
     */
    private int contractLength;

    /**
     * bugetul initial
     */
    private int initialBudget;

    /**
     * costul initial al infrastructurii
     */
    private int initialInfrastructureCost;

    /**
     * costul initial al productiei
     */
    private int initialProductionCost;

    /**
     * profitul sau actual
     */
    private int profit;

    /**
     * pretul actual al contractului pe care il poate
     * oferi consumatorilor noi
     */
    private int price;

    /**
     * numarul total de consumatori care au contract la acesta
     */
    private int numberOfConsumers;

    /**
     * bugetul sau actual
     */
    private int budget;

    /**
     * costul actual al productiei
     */
    private int productionCost;

    /**
     * costul actual al infrastructurii
     */
    private int infrastructureCost;

    /**
     * indica starea financiara a acestuia
     * este 0 initial si se schimba
     * cand intra in faliment
     */
    private int bankrupt;

    /**
     * lista cu contractele actuale
     */
    private HashMap<Integer, Contract> contracts;

    /**
     * lista cu id-urile consumatorilor cu care are contracte
     * in ordinea realizarii intelegerilor
     */
    private ArrayList<Integer> idConsumers;

    /**
     * energia necesara lunar
     */
    private int energyNeededkW;
    /**
     * stategia aleasa
     */
    private EnergyChoiceStrategyType producerStrategy;

    /**
     * lista cu id-urile producatorilor care furnizeaza
     * energia
     */
    private ArrayList<Integer> idProducer;

    /**
     * este 0 daca trebuie sa fie ales un nou producator
     */
    private int hasProducer;


    public Distributors(final int id, final int contractLength, final int initialBudget,
                        final int initialInfrastructureCost,
                        final int energyNeededkW, final EnergyChoiceStrategyType producerStrategy,
                        final int initialProductionCost,
                        final int budget, final int numberOfConsumers, final int productionCost,
                        final int infrastructureCost, final int profit, final int price,
                        final int bankrupt, final int hasProducer) {
        this.id = id;
        this.contractLength = contractLength;
        this.initialBudget = initialBudget;
        this.initialInfrastructureCost = initialInfrastructureCost;
        this.energyNeededkW = energyNeededkW;
        this.producerStrategy = producerStrategy;
        this.initialProductionCost = initialProductionCost;
        this.budget = budget;
        this.profit = profit;
        this.price = price;
        this.productionCost = productionCost;
        this.numberOfConsumers = numberOfConsumers;
        this.infrastructureCost = infrastructureCost;
        this.bankrupt = bankrupt;
        this.hasProducer = hasProducer;
    }

    public int getId() {
        return id;
    }

    public int getContractLength() {
        return contractLength;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    public void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public void setInitialInfrastructureCost(final int initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    public int getInitialProductionCost() {
        return initialProductionCost;
    }

    public void setInitialProductionCost(final int initialProductionCost) {
        this.initialProductionCost = initialProductionCost;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(final int profit) {
        this.profit = profit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }


    public int getNumberOfConsumers() {
        return numberOfConsumers;
    }

    public void setNumberOfConsumers(final int numberOfConsumers) {
        this.numberOfConsumers = numberOfConsumers;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    public int getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(final int productionCost) {
        this.productionCost = productionCost;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public HashMap<Integer, Contract> getContracts() {
        return contracts;
    }

    public void setContracts(final HashMap<Integer, Contract> contracts) {
        this.contracts = contracts;
    }

    public int getBankrupt() {
        return bankrupt;
    }

    public void setBankrupt(final int bankrupt) {
        this.bankrupt = bankrupt;
    }

    public ArrayList<Integer> getIdConsumers() {
        return idConsumers;
    }

    public void setIdConsumers(final ArrayList<Integer> idConsumers) {
        this.idConsumers = idConsumers;
    }

    public int getEnergyNeededkW() {
        return energyNeededkW;
    }

    public void setEnergyNeededkW(int energyNeededkW) {
        this.energyNeededkW = energyNeededkW;
    }

    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public ArrayList<Integer> getIdProducer() {
        return idProducer;
    }

    public void setIdProducer(ArrayList<Integer> idProducer) {
        this.idProducer = idProducer;
    }

    public int getHasProducer() {
        return hasProducer;
    }

    public void setHasProducer(int hasProducer) {
        this.hasProducer = hasProducer;
    }

    @Override
    public void update(final ProducerChanges changes) {
        int i;
        for (i = 0; i < idProducer.size(); i++) {
            if ((int) idProducer.get(i) == changes.getId()) {
                this.hasProducer = 0;
            }
        }
    }
}
