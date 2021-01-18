package data;

public final class ContractFactory {

    /**
     * tipuri de contracte
     */
    public enum ContractType {
        normalContract,
        outputContract
    }

    /**
     * returneaza contractul de tipul dorit
     */
    public ContractEntity getContract(final ContractType type, final int id,
                                      final int price, final int remainedMonths) {

        return switch (type) {
            case outputContract -> new OutputContract(id, price, remainedMonths);
            case normalContract -> new Contract(id, price, remainedMonths);
        };
    }
}
