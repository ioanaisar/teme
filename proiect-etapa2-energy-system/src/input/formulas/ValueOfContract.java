package input.formulas;

/**
 * metoda pentru a calcula pretul contractului
 * cand exista consumatori
 */
public final class ValueOfContract extends Formula {
    @Override
    public int finalPrice(final int infrastructureCost, final int productionCost,
                          final int profit, final int nr) {
        return (int) Math.round(Math.floor((infrastructureCost / nr)) + productionCost + profit);

    }
}
