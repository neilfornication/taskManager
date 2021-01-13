
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ManagerCommands commands = new ManagerCommands();
        System.out.println("Введите команду");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        commands.acts(command);
    }
}
