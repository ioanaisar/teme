package data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * o entitate de tip contract
 */
public abstract class ContractEntity {
    private int price;

    public ContractEntity() {

    }

    public ContractEntity(final int price) {
        this.price = price;
    }

    /**
     * returneaza pretul unui contract
     */
    @JsonProperty(value = "price")
    public int getPrice() {
        return price;
    }

    /**
     * seteaza pretul unui contract
     */
    @JsonProperty(value = "price")
    public void setPrice(final int price) {
        this.price = price;
    }
}
