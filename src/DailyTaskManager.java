import java.util.Scanner;
import java.util.Stack;
import java.util.InputMismatchException;
import java.util.LinkedList; // Better than manually using LinkedList methods :/

public class DailyTaskManager {

/*-------------------------------------------------------------------------------------------------------------------------------------*/

    // ==================== Universal Variable Area :O ==================== //

    // The collection of ANSI color codes to avoid copy pasting
    static String reset = "\u001b[0m", red = "\u001b[31m", blue = "\u001b[34m", purple = "\u001b[35m";
    static String cyan = "\u001b[36m", white = "\u001b[37m", yellow = "\u001b[33m", green = "\u001b[32m";
    static String white_bold = "\u001b[37;1m", yellow_bold = "\u001b[33;1m", cyan_bold = "\u001b[36;1m";

    // Data structures for the program
    String[] Tasks_Arr = {"Eat", "Sleep", "Play", "Sleep part 2", "Sleep part 3"}; // Already predefined array
    Stack<String> Tasks_Array_Status = new Stack<>();
    LinkedList<String> Tasks_LL = new LinkedList<>();
    Stack<String> Tasks_LL_Status = new Stack<>();

    static Scanner scanner = new Scanner(System.in);

/*-------------------------------------------------------------------------------------------------------------------------------------*/

    // ==================== Main Program Area :O ==================== //

    // Checks if input is an integer or not (now in method form)
    public static int Check_Int() {
        while (true) {
            try {
                return scanner.nextInt();
            }
            catch (InputMismatchException e) { 
                System.out.print(red + "You either typed something non Integer or an integer too humongous. Try again : " + reset);
                scanner.next();
            }
        }
    }

    // Clears console depending on if device os is Windows or not
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Could not clear console.");
        }
    }

    public void Array_Menu() {
        while (true) {
        System.out.println("""
================================
What would you like to do today?
1. Check task status
2. Update a task
3. Change task status
4. Back
================================
                        """);
                System.out.print("Enter your choice: ");
                int User_Choice = Check_Int(); 

                switch (User_Choice) {

                    case 1:
                        clearConsole();

                        for (int Update_Index = 0; Update_Index < Tasks_Arr.length; Update_Index++) { 
                            System.out.print((Update_Index+1) + ". " + Tasks_Arr[Update_Index]);
                            
                            if (Tasks_Array_Status.contains(Tasks_Arr[Update_Index])) {
                                System.out.print(green + " (Finished)\n" + reset);
                            }

                            else {
                                System.out.print(red + " (Unfinished)\n" + reset);

                            }
                        }
                        break;
                                       
                    case 2:

                        // Uses task number n, not task index that starts from 0
                        while (true) {
                            System.out.print("Which task would you like to update? (Type task number, 0 to exit) : ");
                            int Update_Index = Check_Int();
                            
                            if (Update_Index > Tasks_Arr.length || Update_Index < 0) {
                                System.out.println(red + "Task not found." + reset);

                            } else if (Update_Index == 0) {
                                clearConsole();
                                break;

                            } else {
                                Update_Index = Update_Index - 1;
                                System.out.print("Update with what task : ");
                                scanner.nextLine();
                                String Update_Task = scanner.nextLine();
                                Tasks_Arr[Update_Index] = Update_Task;
                                clearConsole();
                                System.out.println(yellow + "Succesfully updated!" + reset);
                                break;
                            }
                        }
                        break;

                    case 3:
                        while (true) {
                            System.out.print("Which task would you like to mark as complete? (Type 0 to exit, 9 to undo): ");
                            int Update_Index = Check_Int();

                            if (Update_Index == 0) {
                                clearConsole();
                                break;
                            }
                            
                            else if (Update_Index == 9) {

                                if (Tasks_Array_Status.isEmpty()) {
                                    System.out.println("No completed tasks found...");

                                } else {
                                    Tasks_Array_Status.pop(); // Remove the very last element of the stack
                                    System.out.println("Task has been untasked!");
                                }

                            }

                            // Error handling for out of bound index
                            else if (Update_Index > Tasks_Arr.length || Update_Index < 0) {
                                System.out.println(red + "Task not found." + reset);

                            } 

                            else {
                                Update_Index = Update_Index - 1;
                                String Epic_Task = Tasks_Arr[Update_Index];
                                
                                if (Tasks_Array_Status.contains(Tasks_Arr[Update_Index])){
                                    System.out.println("Task already completed!");
                                } 
                                else {
                                    clearConsole();
                                    Tasks_Array_Status.push(Tasks_Arr[Update_Index]);
                                    System.out.println(yellow + "Succesfully updated!" + reset);
                                }
                            }
                        }
                        break;

                    // Go back to main menu
                    case 4:
                        scanner.nextLine();
                        clearConsole();
                        return;
                        
                    default:
                        clearConsole();
                        System.out.println(red + "Invalid choice!" + reset);
                }
            }
        }
    
public void LinkedList_Menu() {
        while (true) {
        System.out.println("""
================================
What would you like to do today?
1. Check tasks
2. Add a task
3. Remove a task
4. Change Task Status
5. Back
================================
                        """);
                System.out.print("Enter your choice: ");
                int User_Choice = Check_Int();
                switch (User_Choice) {

                    case 1:
                        clearConsole();

                        if (Tasks_LL.isEmpty()) {
                            System.out.println(yellow + "Nothing in the list yet." + reset);
                        } 

                        else {

                            for (int Update_Index = 0; Update_Index < Tasks_LL.size(); Update_Index++) {
                                System.out.print((Update_Index + 1) + ". " + Tasks_LL.get(Update_Index));

                                if (!Tasks_LL_Status.contains(Tasks_LL.get(Update_Index))) {
                                    System.out.println(red + " (Unfinished)" + reset);

                                } else {
                                    System.out.println(green + " (Finished)" + reset);
                                }
                            }
                        }
                        break;
                                       
                    case 2:
                        System.out.print("Type task to add (0 to exit): ");
                        scanner.nextLine();
                        String Update_Task = scanner.nextLine();

                        if (Update_Task.equals("0")) {
                            clearConsole();
                            break;
                        }
                        
                        else {
                            Tasks_LL.add(Update_Task);
                            clearConsole();
                            System.out.println(yellow + "Succesfully updated!" + reset);
                            break;
                        }

                    case 3:
                    
                        while (true) {
                            System.out.print("Type task number to remove (0 to exit): ");
                            int Update_Index = Check_Int();

                            if (Update_Index == 0) {
                                clearConsole();
                                break;
                            }

                            else if (Tasks_LL.isEmpty()){
                                clearConsole();
                                System.out.println(yellow + "Nothing in the list yet." + reset);
                                break;
                            }

                            else if (Update_Index > Tasks_LL.size()) {
                                System.out.println(red + "Task not found." + reset);
                            } 
                            
                            else {
                                Update_Index = Update_Index - 1;
                                Tasks_LL.remove(Update_Index);
                                clearConsole();
                                System.out.println("Yay");
                                break;
                            }
                        } 
                        break;

                    case 4:

                        while (true) {
                            System.out.print("Which task would you like to mark as complete? (Type 0 to exit, 9 to undo): ");
                            int Update_Index = Check_Int();

                            if (Update_Index == 0) {
                                clearConsole();
                                break;
                            }
                            
                            else if (Update_Index == 9) {

                                if (Tasks_LL_Status.isEmpty()) {
                                    System.out.println("No completed tasks found...");

                                } else {
                                    Tasks_LL_Status.remove(Tasks_LL.removeLast());
                                    System.out.println("Task has been untasked!");
                                }

                            }

                            else if (Update_Index > Tasks_LL.size()|| Update_Index < 0) {
                                System.out.println(red + "Task not found." + reset);

                            } 

                            else {
                                Update_Index = Update_Index - 1;

                                if (Tasks_LL_Status.contains(Tasks_LL.get(Update_Index))){
                                    System.out.println("Task already completed!");
                                } 
                                else {
                                    clearConsole();
                                    Tasks_LL_Status.add(Tasks_LL.get(Update_Index));
                                    System.out.println(yellow + "Succesfully updated!" + reset);
                                }
                            }
                        }
                        break;

                    case 5:
                        clearConsole();
                        return;

                    default:
                        clearConsole();
                        System.out.println("Invalid choice");
                }
            }
        }

    public static void main(String[] args) throws Exception {

        DailyTaskManager TaskManager = new DailyTaskManager();
        while (true) {
            System.out.print("""
==============================
Which menu do you want to see?
(0 to exit)
1. Array
2. Linked List
============================== 
Please enter :  """);
            int Menu_Choice = Check_Int();
            if (Menu_Choice == 1) {
                clearConsole();
                TaskManager.Array_Menu();
            } else if (Menu_Choice == 2) {
                clearConsole();
                TaskManager.LinkedList_Menu();
            } else if (Menu_Choice == 0) {
                System.out.print("Bye bye");
                break;
            } else {
                clearConsole();
                System.out.println(red + "Not a valid option!" + reset);
            }
        }
    }
}