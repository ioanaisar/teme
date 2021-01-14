package actions;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * informatia despre schimbarile costurilor unui distribuitor
 */
public final class CostsChanges {
    private int id;
    private int infrastructureCost;
    private int productionCost;


    public CostsChanges(@JsonProperty("id") final int id,
                        @JsonProperty("infrastructureCost") final int infrastructureCost,
                        @JsonProperty("productionCost") final int productionCost) {
        this.id = id;
        this.infrastructureCost = infrastructureCost;
        this.productionCost = productionCost;
    }

    public int getId() {
        return id;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public int getProductionCost() {
        return productionCost;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }


    public void setProductionCost(final int productionCost) {
        this.productionCost = productionCost;
    }
}
