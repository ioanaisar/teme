package output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * utilizata pentru scrierea in fisiere
 * contine lista de distribuitori si de
 * consumatori
 */

public final class Output {

    private List<OutputConsumers> consumers;
    private List<OutputDistributors> distributors;
    private List<OutputProducers> producers;

    public Output(final List<OutputConsumers> consumers,
                  final List<OutputDistributors> distributors,
                  final List<OutputProducers> producers) {
        this.consumers = consumers;
        this.distributors = distributors;
        this.producers = producers;
    }

    @JsonProperty(value = "consumers")
    public List<OutputConsumers> getConsumers() {
        return consumers;
    }

    public void setConsumers(final List<OutputConsumers> consumers) {
        this.consumers = consumers;
    }

    @JsonProperty(value = "distributors")
    public List<OutputDistributors> getDistributors() {
        return distributors;
    }

    public void setDistributors(final List<OutputDistributors> distributors) {
        this.distributors = distributors;
    }

    @JsonProperty(value = "energyProducers")
    public List<OutputProducers> getProducers() {
        return producers;
    }

    public void setProducers(List<OutputProducers> producers) {
        this.producers = producers;
    }
}
