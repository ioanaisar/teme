package input.formulas.strategies;

import data.Producers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

/**
 * clasa pentru aplicarea strategiei Green
 */
public final class GreenStrategy extends EnergyStrategy {
    @Override
    public List<Producers> choseStrategy(final ArrayList<Producers> producers) {
        ArrayList<Producers> producers1 = new ArrayList<Producers>(producers);

        // se sorteaza lista cu producatorii ce ofera energia verde dupa pret
        // si cantitate
        List<Producers> producers2 = producers1.stream().filter(c -> c.getEnergyType().
                equals("WIND") || c.getEnergyType().equals("SOLAR") || c.getEnergyType().
                equals("HYDRO")).sorted(Comparator.comparingDouble(Producers::getPriceKW).
                reversed().thenComparing(Producers::getEnergyPerDistributor).reversed()).
                collect(Collectors.toList());

        // se sorteaza lista cu producatorii ce nu ofera energia verde dupa pret
        // si cantitate
        List<Producers> producers3 = producers1.stream().filter(not(c -> c.getEnergyType().
                equals("WIND") || c.getEnergyType().equals("SOLAR") || c.getEnergyType().
                equals("HYDRO"))).sorted(Comparator.comparingDouble(Producers::getPriceKW).
                reversed().thenComparing(Producers::getEnergyPerDistributor).reversed()).
                collect(Collectors.toList());

        // se unesc cele doua liste(ca un producator sa poate alege pana la epuizare
        // producatorii cu energie verde, apoi pe restul)
        producers2.addAll(producers3);
        return producers2;
    }
}
