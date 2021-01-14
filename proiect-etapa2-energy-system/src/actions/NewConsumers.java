package actions;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * informatia despre un consumator nou
 */
public final class NewConsumers {
    private int id;
    private int initialBudget;
    private int monthlyIncome;


    public NewConsumers(@JsonProperty("id") final int id,
                        @JsonProperty("infrastructureCost") final int initialBudget,
                        @JsonProperty("productionCost") final int monthlyIncome) {
        this.id = id;
        this.initialBudget = initialBudget;
        this.monthlyIncome = monthlyIncome;
    }

    public int getId() {
        return id;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }


    public void setId(final int id) {
        this.id = id;
    }

    public void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}
