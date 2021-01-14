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

    public Output(final List<OutputConsumers> consumers,
                  final List<OutputDistributors> distributors) {
        this.consumers = consumers;
        this.distributors = distributors;
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
}
