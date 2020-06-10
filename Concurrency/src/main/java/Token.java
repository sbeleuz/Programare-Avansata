public class Token implements Comparable<Token> {
    private int number; // from 1 to m, 0 for blank

    public Token(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(Token o) {
        return Integer.compare(this.getNumber(), o.getNumber());
    }
}
