package actions;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * informatia despre schimbarile aparute in energia oferita de un producator
 */
public final class ProducerChanges {
    private int id;
    private int energyPerDistributor;

    public ProducerChanges(@JsonProperty("id") final int id,
                           @JsonProperty("energyPerDistributor") final int energyPerDistributor) {
        this.id = id;
        this.energyPerDistributor = energyPerDistributor;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(final int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

}
