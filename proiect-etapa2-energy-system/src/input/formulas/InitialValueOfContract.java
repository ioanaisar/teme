package input.formulas;

/**
 * metoda pentru a calcula pretul contractului
 * cand nu exista consumatori
 */
public final class InitialValueOfContract extends Formula {
    @Override
    public int finalPrice(final int infrastructureCost, final int productionCost,
                          final int profit, final int nr) {
        return (infrastructureCost + productionCost + profit);
    }
}
