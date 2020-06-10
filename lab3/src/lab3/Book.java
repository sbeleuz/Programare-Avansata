package lab3;

public class Book implements Item {
    private String name;
    private int pageNumber;
    private double value;

    public Book() {
    }

    public Book(String name, int pageNumber, double value) {
        this.name = name;
        this.pageNumber = pageNumber;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getWeight() {
        return (double) (pageNumber / 100);
    }
}
