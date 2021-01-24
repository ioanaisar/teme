package input.formulas.strategies;

import data.Producers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * clasa pentru aplicarea strategiei Price
 */
public final class PriceStrategy extends EnergyStrategy {
    @Override
    public List<Producers> choseStrategy(final ArrayList<Producers> producers) {
        ArrayList<Producers> producers1 = new ArrayList<>(producers);

        // se sorteaza lista cu producatorii dupa pret apoi dupa cantitate
        List<Producers> producers2 = producers1.stream().sorted(Comparator.
                comparingDouble(Producers::getPriceKW).reversed().
                thenComparing(Producers::getEnergyPerDistributor).
                reversed()).collect(Collectors.toList());

        return producers2;
    }
}
