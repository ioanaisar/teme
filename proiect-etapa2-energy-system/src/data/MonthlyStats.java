package data;

import java.util.ArrayList;

/**
 * retine luna si o lista cu id-urile distribuitorilor pe care
 * ii are un producator in acea luna
 */
public final class MonthlyStats {

    /**
     * luna
     */
    private int month;


    /**
     * lista cu id-urile distribuitorilor
     */
    private ArrayList<Integer> distributorsIds;

    public MonthlyStats(final int month, final ArrayList<Integer> distributorsIds) {
        this.month = month;
        this.distributorsIds = distributorsIds;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(final int month) {
        this.month = month;
    }

    public ArrayList<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    public void setDistributorsIds(final ArrayList<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }
}
