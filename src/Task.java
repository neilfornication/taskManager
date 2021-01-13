import java.io.*;
import java.util.Scanner;

public class Task {

    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String taskSearch(File file, String taskNameInput) throws FileNotFoundException {

        Scanner scanFile = new Scanner(file);
        String taskNameLine = "";
        String oldTaskDesc = "";

        while (scanFile.hasNextLine()) {
            taskNameLine = scanFile.nextLine();
            if (taskNameLine.equals(taskNameInput)) {
                oldTaskDesc = scanFile.nextLine();
                break;
            }
            else {
                oldTaskDesc = "null";
            }
        }
        scanFile.close();
        return oldTaskDesc;
    }

    public void setNewTaskData(String oldTaskDesc, String taskNameInput, Task task){

        Scanner scanInput = new Scanner(System.in);
        String newTaskName;
        String newTaskDesc;

        if(!oldTaskDesc.equals("null")){
            System.out.println("Введите новое имя для таска " + taskNameInput);
            newTaskName = scanInput.nextLine();
            task.setName(newTaskName);
            System.out.println("Введите содержание таска " + newTaskName);
            newTaskDesc = scanInput.nextLine();
            task.setDescription(newTaskDesc);
            System.out.println("Новый таск " + "\"" + newTaskName + "\" сохранен");
        }
        else {
            System.out.println("Таск с введенным именем не найден");
        }
    }

    public void putNewTaskDataInFile(File file, String taskNameInput, String oldTaskDesc, Task task) throws IOException {
        Scanner newFileScanner = new Scanner(file);
        String fileInString = "";
        while (newFileScanner.hasNextLine()){
            fileInString += newFileScanner.nextLine() + "\n";
        }
        String newFileInString = "";
        int frontIndex = fileInString.indexOf(taskNameInput);
        int endIndex = fileInString.indexOf(oldTaskDesc) + oldTaskDesc.length();
        newFileInString = fileInString.substring(0, frontIndex) + task.getName() + "\n" + task.getDescription() + fileInString.substring(endIndex);
        FileWriter writer = new FileWriter(file, false);
        writer.write(newFileInString);        ;
        newFileScanner.close();
        writer.close();
    }

    public void deleteTaskFromFile(File file, String taskNameInput, String oldTaskDesc) throws IOException {
        Scanner newFileScanner = new Scanner(file);
        String fileInString = "";
        while (newFileScanner.hasNextLine()){
            fileInString += newFileScanner.nextLine() + "\n";
        }
        String newFileInString = "";
        int frontIndex = fileInString.indexOf(taskNameInput);
        int endIndex = fileInString.indexOf(oldTaskDesc) + oldTaskDesc.length();
        newFileInString = fileInString.substring(0, frontIndex) + fileInString.substring(endIndex);
        FileWriter writer = new FileWriter(file, false);
        writer.write(newFileInString);
        newFileScanner.close();
        writer.close();

    }

}

