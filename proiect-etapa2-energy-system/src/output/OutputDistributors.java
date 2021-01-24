package output;

import data.OutputContract;
import input.formulas.strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

/**
 * informatia despre distribuitori
 * utilizata pentru scrierea in fisiere
 */
public final class OutputDistributors {
    private int id;
    private int energyNeededKW;
    private int contractCost;
    private int budget;
    private EnergyChoiceStrategyType producerStrategy;
    private boolean isBankrupt;
    private ArrayList<OutputContract> contracts;

    public OutputDistributors(final int id, final int energyNeededKW, final int contractCost,
                              final int budget,
                              final EnergyChoiceStrategyType producerStrategy,
                              final boolean isBankrupt,
                              final ArrayList<OutputContract> contracts) {
        this.id = id;
        this.energyNeededKW = energyNeededKW;
        this.contractCost = contractCost;
        this.producerStrategy = producerStrategy;
        this.isBankrupt = isBankrupt;
        this.budget = budget;
        this.contracts = contracts;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public int getContractCost() {
        return contractCost;
    }

    public void setContractCost(int contractCost) {
        this.contractCost = contractCost;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public ArrayList<OutputContract> getContracts() {
        return contracts;
    }

    public void setContracts(final ArrayList<OutputContract> contracts) {
        this.contracts = contracts;
    }
}
