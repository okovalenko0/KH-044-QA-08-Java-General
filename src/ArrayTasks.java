import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayTasks implements Serializable {
    ArrayList<Task> myTasksList = new ArrayList<>();
    public int currentID = 0;
    private String author = "Irina Gormakova";
    private String emailAuthor = "gormakova.ira@gmail.com";
    private String password = "-";

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmailAuthor() {
        return emailAuthor;
    }

    public void setEmailAuthor(String emailAuthor) {
        this.emailAuthor = emailAuthor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addTask(Task newTask) {
        myTasksList.add(newTask);
        currentID++;
    }

    public void reviewAll() {
        myTasksList
                .forEach(System.out::println);
        System.out.println("Which id would you like to see more detailed enter id number");
        int id = 0;
        Scanner scanner = new Scanner(System.in);
        id = scanner.nextInt();
        int finalId = id;
        myTasksList.stream()
                .filter(x -> x.getID() == finalId).peek(x -> System.out.println(x.toString()));
    }

    public void byPriority() {
        boolean flag;
        byte choice = -1;

        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Which priority of task u wish to see");
        do {
            flag = false;
            try {
                System.out.println("1 - Planned");
                System.out.println("2 - Urgent");
                System.out.println("3 - Delegated");
                System.out.println("4 - Trivial");
                choice = Byte.parseByte(br1.readLine());
                if (choice < 1 || choice > 4) throw new MenuExceptions("Incorrect menu item");
            } catch (NumberFormatException | IOException e) {
                System.out.println("Entered number is incorrect");
                System.out.println(e.getMessage());
                flag = true;
            } catch (MenuExceptions e1) {
                System.out.println(e1.getMessage());
                flag = true;
            }
        } while (flag);
        switch (choice) {
            case 1:
                myTasksList.stream()
                        .filter(x -> x.getPriority().equals(TaskPriority.PLANNED))
                        .forEach(System.out::println);
                System.out.println(" ");
                break;
            case 2:
                myTasksList.stream()
                        .filter(x -> x.getPriority().equals(TaskPriority.URGENT))
                        .forEach(System.out::println);
                System.out.println(" ");
                break;
            case 3:
                myTasksList.stream()
                        .filter(x -> x.getPriority().equals(TaskPriority.DELEGATED))
                        .forEach(System.out::println);
                System.out.println(" ");
                break;
            case 4:
                myTasksList.stream()
                        .filter(x -> x.getPriority().equals(TaskPriority.TRIVIAL))
                        .forEach(System.out::println);
                System.out.println(" ");
                break;
            default:
                break;
        }
        int id = 0;
        Scanner scanner = new Scanner(System.in);
        id = scanner.nextInt();
        int finalId = id;
        myTasksList.stream()
                .filter(x -> x.getID() == finalId).peek(x -> System.out.println(x.toString()));
    }


    public Task selectByID() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean flag;

        System.out.println("Full task list:");
        myTasksList
                .forEach(System.out::println);
        int modifiedID;
        do {
            flag = false;
            System.out.println("Please, enter ID of task you want to modify or press ENTER to continue without changing");
            try {
                String s = br.readLine();
                if (!s.equals("")) {
                    modifiedID = Integer.parseInt(s);
                } else return null;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                modifiedID = 0;
            }
            int finalModifiedID = modifiedID;
            if (myTasksList.stream().noneMatch(x -> x.getID() == finalModifiedID)) {
                System.out.println("Task ID " + finalModifiedID + " wasn't found");
                flag = true;
            }
        } while (flag);
        int finalModifiedID1 = modifiedID;
        return myTasksList.stream().filter(x -> x.getID() == finalModifiedID1).findFirst().get();
    }

    public void editCredentials() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        System.out.println("Please, check current credentials and change them if necessary for further correct work of the program");
        System.out.println("Author: " + getAuthor());
        System.out.println("Enter new Author name or press ENTER to continue without changing");
        try {
            s = br.readLine();
            if (!s.equals("")) {
                setAuthor(s);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Author's email: " + getEmailAuthor());
        System.out.println("Enter new Author's email or press ENTER to continue without changing");
        try {
            s = br.readLine();
            if (!s.equals("")) {
                String pattern = "\\w+(\\.\\w+)*@(\\w+\\.)+\\w+";
                Pattern p = Pattern.compile(pattern);

                s = s.trim();
                Matcher m = p.matcher(s);
                if (m.matches()) {
                    setEmailAuthor(s);
                    System.out.println("Author's email has been changed successfully");
                } else {
                    System.out.println("Incorrect email address");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Password: " + getPassword());
        System.out.println("Enter new Password or press ENTER to continue without changing");
        try {
            s = br.readLine();
            if (!s.equals("")) {
                setPassword(s);
                System.out.println("Password has been changed successfully");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

