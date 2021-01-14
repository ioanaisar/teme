package input.formulas;

abstract class Formula {
    static final double PRODUCTION = 0.2;

    /**
     * metoda pentru a calcula pretul unui contract
     */
    abstract int finalPrice(int infrastructureCost, int productionCost,
                            int profit, int nr);

    /**
     * metoda pentru a calcula profitul unui distribuitor
     */
    public int getProfit(final int productionCost) {
        return (int) Math.round(Math.floor(PRODUCTION * (float) productionCost));
    }
}
