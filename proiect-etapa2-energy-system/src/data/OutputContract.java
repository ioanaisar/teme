package data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * contract utilizat in scrierea in fisier
 */
public final class OutputContract extends ContractEntity {
    private int consumerId;
    private int remainedContractMonths;

    public OutputContract(final int consumerId, final int price, final int remainedContractMonths) {
        super(price);
        this.consumerId = consumerId;
        //this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

    @JsonProperty(value = "consumerId")
    public int getConsumerId() {
        return consumerId;
    }


    @JsonProperty(value = "remainedContractMonths")
    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

   /* @JsonProperty(value = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }*/

    @JsonProperty(value = "consumerId")
    public void setConsumerId(final int consumerId) {
        this.consumerId = consumerId;
    }

    @JsonProperty(value = "remainedContractMonths")
    public void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }


}
