package strategies;

import data.Producers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QuantityStrategy extends EnergyStrategy{
    @Override
    public List<Producers> choseStrategy(ArrayList<Producers> producers) {
        ArrayList<Producers> producers1 = new ArrayList<>(producers);
        List<Producers> producers2 = producers1.stream().sorted(Comparator.
                comparingDouble(Producers::getEnergyPerDistributor).
                thenComparing(Producers::getPriceKW).reversed()).collect(Collectors.toList());
        return producers2;
    }

}
