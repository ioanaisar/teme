package input.formulas;

public final class GetFormulaStrategy {

    private static GetFormulaStrategy factory = null;

    private GetFormulaStrategy() {
    }

    public enum StrategyType {
        withConsumers,
        withoutConsumers
    }

    /**
     * metoda va crea o  noua instanta sau o va returna
     * pe cea existenta
     */
    public static GetFormulaStrategy getInstance() {
        if (factory == null) {
            factory = new GetFormulaStrategy();
        }
        return factory;
    }

    /**
     * metoda va decide ce subclasa returneaza
     * in functie de enum-ul primit
     */
    public Formula getFormula(final StrategyType type) {
        if (factory == null) {
            factory = new GetFormulaStrategy();
        }

        return switch (type) {
            case withoutConsumers -> new InitialValueOfContract();
            case withConsumers -> new ValueOfContract();
        };
    }
}
