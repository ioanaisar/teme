package strategies;

import data.Producers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class GreenStrategy extends EnergyStrategy {
    @Override
    public List<Producers> choseStrategy(ArrayList<Producers> producers) {
        ArrayList<Producers> producers1 = new ArrayList<Producers>(producers);
        List<Producers> producers2 = producers1.stream().filter(c -> c.getEnergyType().equals("WIND") || c.getEnergyType().equals("SOLAR") || c.getEnergyType().equals("HYDRO")).sorted(Comparator.
                comparingDouble(Producers::getPriceKW).reversed().
                thenComparing(Producers::getEnergyPerDistributor).reversed()).collect(Collectors.toList());
        List<Producers> producers3 = producers1.stream().filter(not(c -> c.getEnergyType().equals("WIND") || c.getEnergyType().equals("SOLAR") || c.getEnergyType().equals("HYDRO"))).sorted(Comparator.
                comparingDouble(Producers::getPriceKW).reversed().
                thenComparing(Producers::getEnergyPerDistributor).reversed()).collect(Collectors.toList());

        producers2.addAll(producers3);
        return producers2;
    }
}
