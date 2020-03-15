import org.javatuples.Pair;

public class Partition<R, H> {
    Pair<R, H> pair;

    public Partition(R res, H hos) {
        this.pair = new Pair<>(res, hos);
    }

    /**
     * Get first element of a partition
     */
    public R getFirst() {
        return pair.getValue0();
    }

    /**
     * Get second element of a partition
     */
    public H getSecond() {
        return pair.getValue1();
    }

    @Override
    public String toString() {
        return pair.toString();
    }
}
