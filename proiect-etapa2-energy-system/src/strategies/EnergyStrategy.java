package strategies;

import data.Producers;

import java.util.ArrayList;
import java.util.List;

abstract public class EnergyStrategy {
       abstract public List<Producers> choseStrategy(ArrayList<Producers> producers);
   }
