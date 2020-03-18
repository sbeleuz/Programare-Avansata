public class Element<T> {
    private T element;

    public Element(T element) {
        this.element = element;
    }

    /**
     * Get capacity of the "second" element of a partition
     */
    public int getCapacity() {
        if (element instanceof Hospital)
            return ((Hospital) element).getCapacity();
        return -1;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
