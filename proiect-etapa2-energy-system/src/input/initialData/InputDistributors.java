package input.initialData;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class InputDistributors {
    private int id;
    private int contractLength;
    private int initialBudget;
    private int initialInfrastructureCost;
    private int initialProductionCost;

    public InputDistributors(@JsonProperty("id") final int id,
                             @JsonProperty("contractLength") final int contractLength,
                             @JsonProperty("initialBudget") final int initialBudget,
                             @JsonProperty("initialInfrastructureCost")
                             final int initialInfrastructureCost,
                             @JsonProperty("initialProductionCost")
                             final int initialProductionCost) {
        this.id = id;
        this.contractLength = contractLength;
        this.initialBudget = initialBudget;
        this.initialInfrastructureCost = initialInfrastructureCost;
        this.initialProductionCost = initialProductionCost;
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
}
