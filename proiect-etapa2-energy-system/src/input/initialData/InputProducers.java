package input.initialData;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * informatia despre un producator
 */
public final class InputProducers {

    /**
     * id-ul producatorului
     */
    private int id;

    /**
     * tipul energiei
     */
    private String energyType;

    /**
     * numarul maxim de distribuitori permis
     */
    private int maxDistributors;

    /**
     * pretul unui KW
     */
    private double priceKW;

    /**
     * energia oferita lunar unui distribuitor
     */
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
}
