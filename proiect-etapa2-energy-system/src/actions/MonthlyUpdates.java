package actions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * clasa retine 2 liste: una cu noii consumatori si
 * una cu schimbarile costurilor pentru distribuitori
 */
public final class MonthlyUpdates {
    private List<NewConsumers> newConsumers;
    private List<CostsChanges> costsChanges;

    public MonthlyUpdates(@JsonProperty("newConsumers") final List<NewConsumers> newConsumers,
                          @JsonProperty("costsChanges") final List<CostsChanges> costsChanges) {
        this.newConsumers = newConsumers;
        this.costsChanges = costsChanges;
    }

    public List<NewConsumers> getNewConsumers() {
        return newConsumers;
    }

    public List<CostsChanges> getCostsChanges() {
        return costsChanges;
    }

    public void setNewConsumers(final List<NewConsumers> newConsumers) {
        this.newConsumers = newConsumers;
    }


    public void setCostsChanges(final List<CostsChanges> costsChanges) {
        this.costsChanges = costsChanges;
    }
}
