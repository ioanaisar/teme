package output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public final class OutputProducers {
    private int id;
    private int maxDistributors;
    private double priceKW;
    private String energyType;
    private int energyPerDistributor;
    private ArrayList<OutputMonthlyStats> monthlyStats;

    public OutputProducers(final int id,
                           final String energyType,
                           final int maxDistributors,
                           final double priceKW,
                           final int energyPerDistributor,
                           final ArrayList<OutputMonthlyStats> monthlyStats) {
        this.id = id;
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
        this.monthlyStats = monthlyStats;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    @JsonProperty(value = "energyType")
    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(final String energyType) {
        this.energyType = energyType;
    }

    @JsonProperty(value = "maxDistributors")
    public int getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(final int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    @JsonProperty(value = "priceKW")
    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(final double priceKW) {
        this.priceKW = priceKW;
    }

    @JsonProperty(value = "energyPerDistributor")
    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(final int energyPersDistributor) {
        this.energyPerDistributor = energyPersDistributor;
    }

    @JsonProperty(value = "monthlyStats")
    public ArrayList<OutputMonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(final ArrayList<OutputMonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }
}
