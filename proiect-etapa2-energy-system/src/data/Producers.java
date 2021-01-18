package data;

import java.util.ArrayList;

public final class Producers {
    private int id;
    private String energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;
    private ArrayList<MonthlyStats> monthlyStats;
    private int nrDistributors;

    public Producers(final int id,
                     final String energyType,
                     final int maxDistributors,
                     final double priceKW,
                     final int energyPerDistributor, final int nrDistributors) {
        this.id = id;
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
        this.nrDistributors = nrDistributors;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(final String energyType) {
        this.energyType = energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(final int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(final double priceKW) {
        this.priceKW = priceKW;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(final int energyPersDistributor) {
        this.energyPerDistributor = energyPersDistributor;
    }

    public ArrayList<MonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(final ArrayList<MonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    public int getNrDistributors() {
        return nrDistributors;
    }

    public void setNrDistributors(final int nrDistributors) {
        this.nrDistributors = nrDistributors;
    }
}

