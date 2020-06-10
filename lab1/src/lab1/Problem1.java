package lab1;

/**
 * @author Stefan Beleuz
 */

public class Problem1 {
    public static void main(String[] args) {
        Problem1 problem1 = new Problem1();
        problem1.compulsory();
    }


    private void compulsory() {
        System.out.println("Hello World!");

        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        int n = (int) (Math.random() * 1_000_000);
        n = n * 3;
        n = n + 0b10101;
        n = n + 0xff;
        n = n * 6;

        int result = 0;
        while (n > 9) {
            result = 0;
            while (n > 0) {
                result = result + n % 10;
                n = n / 10;
            }
            n = result;
        }

        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
}
