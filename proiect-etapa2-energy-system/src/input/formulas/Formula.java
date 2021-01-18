package input.formulas;

import java.util.ArrayList;

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

    public int getProductionCost(final ArrayList<Double> priceKW, final ArrayList<Integer> energyPerDistributor) {
        int i;
        double cost;
        cost = 0;
        for (i = 0; i < priceKW.size(); i++) {
            cost += (priceKW.get(i) * energyPerDistributor.get(i));
           // System.out.print("\n"+ "     "+ energyPerDistributor.get(i) +"  ");
        }

        return (int) Math.round(Math.floor((float) cost / 10));
    }
}
