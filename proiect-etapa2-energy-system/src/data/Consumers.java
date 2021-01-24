package data;

/**
 * informatia despre un consumator
 */
public final class Consumers {
    /**
     * id-ul sau
     */
    private final int id;

    /**
     * bugetul initial
     */
    private int initialBudget;

    /**
     * venitul sau lunar
     */
    private int monthlyIncome;

    /**
     * bugetul actual
     */
    private int budget;

    /**
     * id-ul distribuitorului cu care are contract
     */
    private int idDistributor;

    /**
     * penalizarea in caz ca nu a platit o luna
     */
    private int penalization;

    /**
     * durata contractului curent
     */
    private int durationofContract;

    /**
     * campul indica starea financiara a consumatorului
     * este 0 initial
     * 1 daca nu a platit o luna
     * 2 daca a intrat in faliment
     */
    private int bankrupt;

    /**
     * pretul contractului actual pe care il plateste lunar
     */
    private int price;

    /**
     * indica daca consumatorul tocmai si a ales un distribuitor nou
     */
    private int newDistributors;


    public Consumers(final int id, final int initialBudget, final int monthlyIncome,
                     final int budget, final int penalization, final int bankrupt) {
        this.id = id;
        this.initialBudget = initialBudget;
        this.monthlyIncome = monthlyIncome;
        this.budget = budget;
        this.penalization = penalization;
        this.bankrupt = bankrupt;
    }

    public int getId() {
        return id;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    public int getIdDistributor() {
        return idDistributor;
    }

    public void setIdDistributor(final int idDistributor) {
        this.idDistributor = idDistributor;
    }

    public int getPenalization() {
        return penalization;
    }

    public void setPenalization(final int penalization) {
        this.penalization = penalization;
    }

    public int getDurationofContract() {
        return durationofContract;
    }

    public void setDurationofContract(final int duration) {
        this.durationofContract = duration;
    }

    public int getBankrupt() {
        return bankrupt;
    }

    public void setBankrupt(final int bankrupt) {
        this.bankrupt = bankrupt;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public int getNewDistributors() {
        return newDistributors;
    }

    public void setNewDistributors(int newDistributors) {
        this.newDistributors = newDistributors;
    }
}
