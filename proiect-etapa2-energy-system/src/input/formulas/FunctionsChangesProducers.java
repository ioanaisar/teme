package input.formulas;

import data.Distributors;
import data.MonthlyStats;
import data.Producers;
import strategies.ChoiceStrategy;
import strategies.EnergyStrategy;

import java.util.ArrayList;
import java.util.List;

public class FunctionsChangesProducers {
    public void setMonthlyStats(int nrRound, ArrayList<Producers> producers) {
        int i, j;

        for (i = 0; i < producers.size(); i++) {
            ArrayList<MonthlyStats> monthlyStats = new ArrayList<>();
            for (j = 0; j <= nrRound; j++) {
                ArrayList<Integer> ids = new ArrayList<>();
                MonthlyStats stat = new MonthlyStats(j, ids);
                monthlyStats.add(stat);
            }
            producers.get(i).setMonthlyStats(monthlyStats);

        }
    }

    public void chooseInitialProducers(ArrayList<Distributors> distributors, ArrayList<Producers> producers, int month) {
        int i, j, k, sum;
        List<Producers> sortedProducers;
        ChoiceStrategy energyChooseStrategy = new ChoiceStrategy();
        GetFormulaStrategy strategy = GetFormulaStrategy.getInstance();
        Formula formula = strategy.getFormula(GetFormulaStrategy.StrategyType.withoutConsumers);

        for (i = 0; i < distributors.size(); i++) {
            if (distributors.get(i).getHasProducer() == 0) {

                if (distributors.get(i).getIdProducer() != null) {
                    for (j = 0; j < distributors.get(i).getIdProducer().size(); j++) {
                        for (k = 0; k < producers.size(); k++) {
                            if (producers.get(k).getId() == distributors.get(i).getIdProducer().get(j)) {
                                int nr = producers.get(k).getNrDistributors();
                                nr--;
                                producers.get(k).setNrDistributors(nr);
                            }
                        }
                    }
                }
                distributors.get(i).setIdProducer(new ArrayList<>());
                EnergyStrategy energyStrategy = energyChooseStrategy.getStrategy(distributors.get(i).getProducerStrategy());
                sortedProducers = energyStrategy.choseStrategy(producers);
                /*System.out.print(" \nalege "  + distributors.get(i).getId() + " \n");
                for(j=0;j< sortedProducers.size();j++){
                    System.out.print(" id " + sortedProducers.get(j).getId());
                }
                System.out.print(" \n\n" );
*/
                ArrayList<Double> costPerKW = new ArrayList<>();
                ArrayList<Integer> cantity = new ArrayList<>();
                ArrayList<Integer> ids = new ArrayList<>();
                j = 0;
                sum = 0;
                while (sum < distributors.get(i).getEnergyNeededkW() && sortedProducers.size() != 0 && j < sortedProducers.size()) {
                    // System.out.print(" \n variante  "  + distributors.get(i).getId() + " nr dist " + sortedProducers.get(j).getNrDistributors()+ " \n");
                    if (sortedProducers.get(j).getMaxDistributors() > sortedProducers.get(j).getNrDistributors()) {
                        sum += sortedProducers.get(j).getEnergyPerDistributor();
                        costPerKW.add(sortedProducers.get(j).getPriceKW());
                        cantity.add(sortedProducers.get(j).getEnergyPerDistributor());
                        ids.add(sortedProducers.get(j).getId());

                        for (k = 0; k < producers.size(); k++) {
                            if (producers.get(k).getId() == sortedProducers.get(j).getId()) {
                                int nrDistributors = producers.get(k).getNrDistributors();
                                nrDistributors++;
                                producers.get(k).setNrDistributors(nrDistributors);
                            }
                        }
                    }
                    j++;
                }

                distributors.get(i).setProductionCost(formula.getProductionCost(costPerKW, cantity));
                distributors.get(i).setIdProducer(ids);
                distributors.get(i).setHasProducer(1);
            }
        }
        for (i = 0; i < distributors.size(); i++) {
            for (j = 0; j < distributors.get(i).getIdProducer().size(); j++) {
                for (k = 0; k < producers.size(); k++) {
                    if (producers.get(k).getId() == distributors.get(i).getIdProducer().get(j)) {
                        ArrayList<MonthlyStats> monthlyStats = producers.get(k).getMonthlyStats();

                        ArrayList<Integer> distributorsId = monthlyStats.get(month).getDistributorsIds();
                        distributorsId.add(distributors.get(i).getId());
                        monthlyStats.get(month).setDistributorsIds(distributorsId);
                        producers.get(k).setMonthlyStats(monthlyStats);

                    }
                }
            }
        }
    }
}
