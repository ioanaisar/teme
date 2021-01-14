package input;

import actions.MonthlyUpdates;
import com.fasterxml.jackson.annotation.JsonProperty;
import input.initialData.InitialData;

import java.util.List;

/**
 * utilizata pentru retinerea datelor de intrare
 */
public final class Input {

    /**
     * numarul de runde
     */
    private int numberOfTurns;

    /**
     * clasa cu liste de consumatori si distribuitori
     */
    private InitialData initialData;

    /**
     * lista cu schimbarile la fiecare runda
     */
    private List<MonthlyUpdates> monthlyUpdates;

    public Input(@JsonProperty("numberOfTurns") final int numberOfTurns,
                 @JsonProperty("initialData") final InitialData initialData,
                 @JsonProperty("monthlyUpdates") final List<MonthlyUpdates> monthlyUpdates) {
        this.numberOfTurns = numberOfTurns;
        this.initialData = initialData;
        this.monthlyUpdates = monthlyUpdates;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public List<MonthlyUpdates> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    public void setMonthlyUpdates(final List<MonthlyUpdates> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }
}
