package data;

/**
 * informatia despre un contract utilizat in simulare
 */
public final class Contract extends ContractEntity {
    private int id;
    private int remainedMonths;

    public Contract(final int id, final int price, final int remainedMonths) {
        super(price);
        this.id = id;
        this.remainedMonths = remainedMonths;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getRemainedMonths() {
        return remainedMonths;
    }

    public void setRemainedMonths(final int remainedMonths) {
        this.remainedMonths = remainedMonths;
    }

}
