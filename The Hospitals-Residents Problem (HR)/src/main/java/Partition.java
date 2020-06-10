import org.javatuples.Pair;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Partition<?, ?> partition = (Partition<?, ?>) o;

        return Objects.equals(pair, partition.pair);
    }

    @Override
    public int hashCode() {
        return pair != null ? pair.hashCode() : 0;
    }

    @Override
    public String toString() {
        return pair.toString();
    }
}
