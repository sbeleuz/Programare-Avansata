package ro.uaic.info.java.lab11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import ro.uaic.info.java.lab11.gomoku_server.app.GameServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableSwagger2
public class Lab11Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab11Application.class, args);
        GameServer gameServer = new GameServer();
    }

}
