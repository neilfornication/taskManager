import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ManagerCommands commands = new ManagerCommands();
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (!command.equals("exit")) {
            System.out.println("Введите команду");
            command = scanner.nextLine();
            commands.acts(command);
        }

    }
}
