package input.formulas.strategies;

/**
 * clasa ce selecteaza stategia aplicata
 */
public class ChoiceStrategy {

    /**
     * metoda ce selecteaza stategia aplicata
     */
    public final EnergyStrategy getStrategy(final EnergyChoiceStrategyType type) {

        return switch (type) {
            case GREEN -> new GreenStrategy();
            case PRICE -> new PriceStrategy();
            case QUANTITY -> new QuantityStrategy();
        };
    }
}
