package output;

import data.OutputContract;

import java.util.ArrayList;

/**
 * informatia despre distribuitori
 * utilizata pentru scrierea in fisiere
 */
public final class OutputDistributors {
    private int id;
    private int budget;
    private boolean isBankrupt;
    private ArrayList<OutputContract> contracts;

    public OutputDistributors(final int id, final int budget, final boolean isBankrupt,
                              final ArrayList<OutputContract> contracts) {
        this.id = id;
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

    public ArrayList<OutputContract> getContracts() {
        return contracts;
    }

    public void setContracts(final ArrayList<OutputContract> contracts) {
        this.contracts = contracts;
    }
}
