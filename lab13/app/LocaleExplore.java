package app;

import com.Command;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    private static ResourceBundle resourceBundle;
    private static final String baseName = "res.Messages";

    public static void setResourceBundle(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(baseName, locale);
    }

    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        resourceBundle = ResourceBundle.getBundle(baseName, locale);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(LocaleExplore.message("prompt"));
            String commandName = scanner.next();

            if (commandName.equalsIgnoreCase("exit")) {
                break;
            }

            String[] params = scanner.nextLine().trim().split("\\s+");
            try {
                // The command name is actually the class name
                Class<?> clazz = Class.forName(commandName);
                Command command = (Command) clazz.getConstructor().newInstance();
                command.execute(params);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                System.out.println(LocaleExplore.message("invalid"));
            }
        }
    }

    public static String message(String key, String... arguments) {
        String pattern = resourceBundle.getString(key);
        return new MessageFormat(pattern).format(arguments);
    }
}
