package input.initialData;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public final class InitialData {
    private List<InputConsumers> consumers;
    private List<InputDistributors> distributors;


    public InitialData(@JsonProperty("consumers") final List<InputConsumers> consumers,
                       @JsonProperty("distributors") final List<InputDistributors> distributors) {
        this.consumers = consumers;
        this.distributors = distributors;
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

}
