package input.initialData;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class InputConsumers {
    private int id;
    private int initialBudget;
    private int monthlyIncome;

    public InputConsumers(@JsonProperty("id") final int id,
                          @JsonProperty("initialBudget") final int initialBudget,
                          @JsonProperty("monthlyIncome") final int monthlyIncome) {
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
