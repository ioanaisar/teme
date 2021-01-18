package input.initialData;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public final class InitialData {
    private List<InputConsumers> consumers;
    private List<InputDistributors> distributors;
    private List<InputProducers> producers;


    public InitialData(@JsonProperty("consumers") final List<InputConsumers> consumers,
                       @JsonProperty("distributors") final List<InputDistributors> distributors,
                       @JsonProperty("producers") final List<InputProducers> producers) {
        this.consumers = consumers;
        this.distributors = distributors;
        this.producers = producers;
    }

    public List<InputConsumers> getConsumers() {
        return consumers;
    }

    public List<InputDistributors> getDistributors() {
        return distributors;
    }

    public void setConsumers(final List<InputConsumers> consumers) {
        this.consumers = consumers;
    }

    public void setDistributors(final List<InputDistributors> distributors) {
        this.distributors = distributors;
    }

    public List<InputProducers> getProducers() {
        return producers;
    }

    public void setProducers(List<InputProducers> producers) {
        this.producers = producers;
    }
}
