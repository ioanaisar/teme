package actions;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * informatia despre schimbarile costurilor unui distribuitor
 */
public final class DistributorChanges {
    private int id;
    private int infrastructureCost;

    public DistributorChanges(@JsonProperty("id") final int id,
                              @JsonProperty("infrastructureCost") final int infrastructureCost) {
        this.id = id;
        this.infrastructureCost = infrastructureCost;
    }

    public int getId() {
        return id;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }


    public void setId(final int id) {
        this.id = id;
    }

    public void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

}
