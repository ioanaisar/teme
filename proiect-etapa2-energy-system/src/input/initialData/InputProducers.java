package input.initialData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputProducers {
    private int id;
    private String energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;

    public InputProducers(@JsonProperty("id") final int id,
                          @JsonProperty("energyType") final String energyType,
                          @JsonProperty("maxDistributors") final int maxDistributors,
                          @JsonProperty("priceKW") final double priceKW,
                          @JsonProperty("energyPersDistributor") final int energyPerDistributor) {
        this.id = id;
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(int energyPersDistributor) {
        this.energyPerDistributor = energyPersDistributor;
    }
}
