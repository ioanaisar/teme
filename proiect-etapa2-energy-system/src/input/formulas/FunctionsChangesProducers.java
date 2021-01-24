package input.formulas;

import data.Distributors;
import data.MonthlyStats;
import data.Producers;
import input.formulas.strategies.ChoiceStrategy;
import input.formulas.strategies.EnergyStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * clasa contine metode pentru actualizarea relatiilor intre producatori
 * si distribuitori
 */
public final class FunctionsChangesProducers {

    /**
     * metoda seteaza statisticile lunare la inceputul simularii
     */
    public void setMonthlyStats(final int nrRound, final ArrayList<Producers> producers) {
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

    /**
     * metoda va sterge listele distribuitorilor cu producatorii, in cazul in care trebuie
     * realesi
     * metoda va alege noii producatori daca este cazul
     */
    public void chooseInitialProducers(final ArrayList<Distributors> distributors,
                                       final ArrayList<Producers> producers, final int month) {
        int i, j, k, sum;

        List<Producers> sortedProducers;
        ChoiceStrategy energyChooseStrategy = new ChoiceStrategy();
        GetFormulaStrategy strategy = GetFormulaStrategy.getInstance();
        Formula formula = strategy.getFormula(GetFormulaStrategy.StrategyType.withoutConsumers);

        for (i = 0; i < distributors.size(); i++) {
            if (distributors.get(i).getHasProducer() == 0) {

                // se scade numarul distribuitorilor ai unui producator daca anumiti
                // distribuitori isi vor realege producatorii
                if (distributors.get(i).getIdProducer() != null) {
                    for (j = 0; j < distributors.get(i).getIdProducer().size(); j++) {
                        for (k = 0; k < producers.size(); k++) {
                            if (producers.get(k).getId() == distributors.get(i).
                                    getIdProducer().get(j)) {

                                int nr = producers.get(k).getNrDistributors();
                                nr--;
                                producers.get(k).setNrDistributors(nr);
                            }
                        }
                    }
                }

                // distribuitorul ramas fara producatori isi reinitializeaza lista cu id-uri
                distributors.get(i).setIdProducer(new ArrayList<>());

                // se alege strategia utilizata
                EnergyStrategy energyStrategy = energyChooseStrategy.
                        getStrategy(distributors.get(i).getProducerStrategy());

                // in functie de strategie se calculeaza o lista ordonata
                // cu producatorii pe care ii poate alege
                sortedProducers = energyStrategy.choseStrategy(producers);

                // lista cu costurile consumatorilor alesi
                ArrayList<Double> costPerKW = new ArrayList<>();

                // lista cu cantitatile de energie oferite
                ArrayList<Integer> cantity = new ArrayList<>();

                // lista cu id-urile consumatorilor alesi
                ArrayList<Integer> ids = new ArrayList<>();
                j = 0;
                sum = 0;

                // se parcurge lista cu producatori pana se atinge cantitatea de energie
                // nesara acelui distribuitor, se retin preturile, cantitatea de energie
                // oferita de fiecare producator si id-ul sau
                while (sum < distributors.get(i).getEnergyNeededkW()
                        && sortedProducers.size() != 0 && j < sortedProducers.size()) {

                    if (sortedProducers.get(j).getMaxDistributors() > sortedProducers.get(j).
                            getNrDistributors()) {

                        sum += sortedProducers.get(j).getEnergyPerDistributor();
                        costPerKW.add(sortedProducers.get(j).getPriceKW());
                        cantity.add(sortedProducers.get(j).getEnergyPerDistributor());
                        ids.add(sortedProducers.get(j).getId());

                        // se creste numarul de distribuitori ai producatorului ales
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

                // se calculeaza si se seteaza noul cost al productiei
                distributors.get(i).setProductionCost(formula.
                        getProductionCost(costPerKW, cantity));
                distributors.get(i).setIdProducer(ids);
                distributors.get(i).setHasProducer(1);
            }
        }

        // se calculeaza statisticile producatorilor pentru luna respectivaa
        for (i = 0; i < distributors.size(); i++) {
            for (j = 0; j < distributors.get(i).getIdProducer().size(); j++) {
                for (k = 0; k < producers.size(); k++) {
                    if (producers.get(k).getId() == distributors.get(i).getIdProducer().get(j)) {
                        ArrayList<MonthlyStats> monthlyStats = producers.get(k).getMonthlyStats();

                        ArrayList<Integer> distributorsId = monthlyStats.get(month).
                                getDistributorsIds();
                        distributorsId.add(distributors.get(i).getId());
                        monthlyStats.get(month).setDistributorsIds(distributorsId);
                        producers.get(k).setMonthlyStats(monthlyStats);

                    }
                }
            }
        }
    }
}
