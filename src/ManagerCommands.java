import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ManagerCommands {
    private Scanner sc = new Scanner(System.in);
    private String command = sc.nextLine();
    private String pathName = "C:\\Users\\yoshi\\Desktop\\123.txt";
    File file = new File(pathName);
    private String[] commands = new String[] {"create", "edit", "delete", "show task", "tasks", "help", "exit"};



    public void acts(String command) throws IOException {
        if (command.equals("create")){
            createTask();
        }
        else if(command.equals("edit")) {
            editTask(this.file);
        }
        else if(command.equals("delete")) {
            System.out.println("Please enter the task name you want to delete");
            Scanner scanInput = new Scanner(System.in);
            String taskNameInput = scanInput.nextLine();
            deleteTask(this.file, taskNameInput);
        }
        else if(command.equals("show task")) {
            System.out.println("Please enter the task name to show");
            Scanner scanInput = new Scanner(System.in);
            String taskNameInput = scanInput.nextLine();
            showTask(this.file, taskNameInput);
        }
        else if(command.equals("exit")){
            System.out.println("Bye");
        }
        else if(command.equals("tasks")){
            showAllTasks(this.file);
        }
        else if(command.equals("help")){
            showCommands();
        }
        else{
            System.out.println(command +" unknown, please try again");
            Scanner scan = new Scanner(System.in);
            String newCommand = scan.nextLine();
            this.command = newCommand;
        }
    }

    public void createTask() throws IOException {

        FileWriter writer = new FileWriter(file, true);
        Task task = new Task();
        System.out.println("Please enter the task name");
        Scanner scanner = new Scanner(System.in);
        String newTaskName = scanner.nextLine();
        task.setName(newTaskName);
        writer.write(task.getName() + "\n");
        System.out.println("Please enter the task description");
        String newTaskDescription = scanner.nextLine();
        task.setDescription(newTaskDescription);
        writer.write(task.getDescription() + "\n");
        System.out.println("Task " + newTaskName + " is saved");
        writer.close();
    }
    //
    public void editTask(File file) throws IOException {
        Scanner scanFile = new Scanner(file);
        Scanner scanInput = new Scanner(System.in);
        System.out.println("Please enter the task name you want to edit");
        String taskNameInput = scanInput.nextLine();
        Task task = new Task();
        String oldTaskDesc = task.taskSearch(file, taskNameInput);
        if(!oldTaskDesc.equals("null")){
            task.setNewTaskData(oldTaskDesc, taskNameInput, task);
            task.putNewTaskDataInFile(file, taskNameInput, oldTaskDesc, task);
        }
        else{
            System.out.println("Entered task not found, please try again");
        }

    }


    public void deleteTask(File file, String taskNameInput) throws IOException {
        Task task = new Task();
        String oldTaskDesc = task.taskSearch(file, taskNameInput);
        if(!oldTaskDesc.equals("null")) {
            task.deleteTaskFromFile(file, taskNameInput, oldTaskDesc);
            System.out.println("Task " + taskNameInput + " is deleted");
        }
        else{
            System.out.println("Entered task not found, please try again");
        }

    }

    public void showTask(File file, String taskNameInput) throws FileNotFoundException {
        Task task = new Task();
        String oldTaskDesc = task.taskSearch(file, taskNameInput);
        if(oldTaskDesc.equals("null")){
            System.out.println("Таск с введенным именем не найден");
        }
        else {
            System.out.println(taskNameInput + "\n" + oldTaskDesc);
        }
    }

    public void showAllTasks(File file) throws FileNotFoundException {
        Task task = new Task();
        String taskNameLine = "";
        int counter = 1;
        Scanner fileScanner = new Scanner(file);
        while(fileScanner.hasNextLine()){
            if(counter % 2 == 1) {
                taskNameLine = fileScanner.nextLine();
                System.out.println(taskNameLine);
            }
            else{
                taskNameLine = fileScanner.nextLine();
            }
            counter += 1;
        }
    }

    public void showCommands(){
        for(int i = 0; i < commands.length; i++){
            System.out.println(commands[i]);
        }
    }
}
