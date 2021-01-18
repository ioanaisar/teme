package output;

import java.util.ArrayList;

public class OutputMonthlyStats {
    private int month;
    private ArrayList<Integer> distributorsIds;

    public OutputMonthlyStats(int month, ArrayList<Integer> distributorsIds) {
        this.month = month;
        this.distributorsIds = distributorsIds;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public ArrayList<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    public void setDistributorsIds(ArrayList<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }

}
