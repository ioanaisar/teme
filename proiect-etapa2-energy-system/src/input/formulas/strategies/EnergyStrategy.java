package input.formulas.strategies;

import data.Producers;

import java.util.ArrayList;
import java.util.List;

/**
 * clasa abstracta ce contine metoda pentru a forma
 * lista sortata de producatori dupa stategie
 */
public abstract class EnergyStrategy {
    /**
     * metoda ce returneaza lista ordonata de producatori
     */
    public abstract List<Producers> choseStrategy(ArrayList<Producers> producers);
}
