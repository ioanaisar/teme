package strategies;

public class ChoiceStrategy {
    public EnergyStrategy getStrategy(final EnergyChoiceStrategyType type) {

        return switch (type) {
            case GREEN -> new GreenStrategy();
            case PRICE -> new PriceStrategy();
            case QUANTITY -> new QuantityStrategy();
        };
    }
}
