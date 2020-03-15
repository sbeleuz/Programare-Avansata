import java.util.ArrayList;
import java.util.List;

public class Matching<R, H> {
    private List<Partition<R, H>> partitionList;

    public Matching() {
        this.partitionList = new ArrayList<>();
    }

    public List<Partition<R, H>> getPartitionList() {
        return partitionList;
    }

    /**
     * Add a new partition to current matchingW
     */
    public void add(Partition<R, H> p) {
        partitionList.add(p);
    }

    @Override
    public String toString() {
        return "Matching{" +
                partitionList +
                '}';
    }
}
