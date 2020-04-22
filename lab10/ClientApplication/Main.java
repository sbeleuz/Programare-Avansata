import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        GameClient gameClient = new GameClient();
        try {
            gameClient.play();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
