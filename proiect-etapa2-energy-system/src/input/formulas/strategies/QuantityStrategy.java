package input.formulas.strategies;

import data.Producers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * clasa pentru aplicarea strategiei Quantity
 */
public final class QuantityStrategy extends EnergyStrategy {
    @Override
    public List<Producers> choseStrategy(final ArrayList<Producers> producers) {
        ArrayList<Producers> producers1 = new ArrayList<>(producers);


        // se sorteaza lista cu producatori dupa cantitate apoi dupa pret
        List<Producers> producers2 = producers1.stream().sorted(Comparator.
                comparingDouble(Producers::getEnergyPerDistributor).
                thenComparing(Producers::getPriceKW).reversed()).collect(Collectors.toList());

        return producers2;
    }

}
