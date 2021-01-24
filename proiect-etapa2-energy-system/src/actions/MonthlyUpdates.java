package actions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * clasa retine 3 liste: una cu noii consumatori,
 * una cu schimbarile costurilor pentru distribuitori
 * una cu schimbarile in energia oferita de producatori
 */
public final class MonthlyUpdates {
    private List<NewConsumers> newConsumers;
    private List<DistributorChanges> distributorChanges;
    private List<ProducerChanges> producerChanges;

    public MonthlyUpdates(@JsonProperty("newConsumers") final List<NewConsumers> newConsumers,
                          @JsonProperty("distributorChanges")
                          final List<DistributorChanges> distributorChanges,
                          @JsonProperty("producerChanges")
                          final List<ProducerChanges> producerChanges) {

        this.newConsumers = newConsumers;
        this.distributorChanges = distributorChanges;
        this.producerChanges = producerChanges;
    }

    public List<NewConsumers> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final List<NewConsumers> newConsumers) {
        this.newConsumers = newConsumers;
    }


    public List<ProducerChanges> getProducerChanges() {
        return producerChanges;
    }

    public void setProducerChanges(List<ProducerChanges> producerChanges) {
        this.producerChanges = producerChanges;
    }

    public List<DistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public void setDistributorChanges(List<DistributorChanges> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }
}
