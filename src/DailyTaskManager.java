import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList; // Better than manually using LinkedList methods :/

public class DailyTaskManager {

/*-------------------------------------------------------------------------------------------------------------------------------------*/

    // ==================== Universal Variable Area :O ==================== //

    static String reset = "\u001b[0m", red = "\u001b[31m", blue = "\u001b[34m", purple = "\u001b[35m";
    static String cyan = "\u001b[36m", white = "\u001b[37m", yellow = "\u001b[33m", green = "\u001b[32m";
    static String white_bold = "\u001b[37;1m", yellow_bold = "\u001b[33;1m", cyan_bold = "\u001b[36;1m";

    String[] Tasks = {"Eat", "Sleep", "Play", "Sleep part 2", "Sleep part 3"};
    Stack<String> Tasks_Status = new Stack<>();
    LinkedList<String> Tasks_LL = new LinkedList<>();

    static Scanner scanner = new Scanner(System.in);

/*-------------------------------------------------------------------------------------------------------------------------------------*/

    public static int Check_Int() {
        while (!scanner.hasNextInt()) { 
            System.out.println(red + "Invalid input! Please enter a number!" + reset);
            scanner.next(); 
        }
        return scanner.nextInt();
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
                    System.out.print("\033[H\033[2J");

                    for (int Update_Index = 0; Update_Index < Tasks.length; Update_Index++) { // Rather reuse another object than making a new one
                        System.out.print((Update_Index+1) + ". " + Tasks[Update_Index]);
                        
                        if (Tasks_Status.contains(Tasks[Update_Index])) {
                            System.out.print(green + " (Finished)\n" + reset);
                        }

                        else {
                            System.out.print(red + " (Unfinished)\n" + reset);

                        }
                    }
                    break;
                                       
                    case 2:
                        while (true) {
                            System.out.print("Which task would you like to update? (Type the index) : ");
                            int Update_Index = Check_Int();
                            if (Tasks.length < Update_Index) {
                                System.out.println("hell no");

                            } else {
                                Update_Index = Update_Index - 1;
                                System.out.print("Update with what task : ");
                                scanner.nextLine();
                                String Update_Task = scanner.nextLine();
                                Tasks[Update_Index] = Update_Task;
                                System.out.print("\033[H\033[2J");
                                System.out.println("Succesfully updated!");
                                break;
                            }
                        }
                        break;

                    case 3:
                        while (true) {
                            System.out.print("Which task would you like to mark as complete? (Type 0 to exit, 9 to undo): ");
                            int Update_Index = Check_Int();
                            if (Tasks.length < Update_Index && Update_Index != 9 ) {
                                System.out.println("hell no");

                            } else {
                                Update_Index = Update_Index - 1;
                                System.out.print("\033[H\033[2J");

                                if (Update_Index == -1) {
                                    break;
                                
                                } else if (Update_Index == 8) {
                                    if (Tasks_Status.isEmpty()) {
                                        System.out.println("No completed tasks found...");
                                    } else {
                                        Tasks_Status.pop();
                                        System.out.println("Task has been untasked!");
                                    }

                                } else if (Tasks_Status.contains(Tasks[Update_Index])){
                                    System.out.println("Task already completed!");

                                }

                                else {
                                    Tasks_Status.push(Tasks[Update_Index]);
                                    System.out.println("Succesfully updated!");
                                }
                            }
                        }
                        break;

                    case 4:
                        scanner.nextLine();
                        System.out.print("\033[H\033[2J");
                        return;
                        
                    default:
                    
                        System.out.println("Invalid choice");
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
4. Back
================================
                        """);
                System.out.print("Enter your choice: ");
                int User_Choice = Check_Int();
                switch (User_Choice) {
                    case 1:
                    System.out.print("\033[H\033[2J");
                    if (Tasks_LL.isEmpty()) {
                        System.out.println("List is empty lmao");
                    } 
                    else {
                        for (String tasks : Tasks_LL) {
                            System.out.println("-" + tasks);
                        }
                    }
                    break;
                                       
                    case 2:
                        System.out.print("Type task to add : ");
                        scanner.nextLine();
                        String Update_Task = scanner.nextLine();
                        Tasks_LL.add(Update_Task);
                        System.out.print("\033[H\033[2J");
                        System.out.println("Succesfully updated!");
                        break;

                    case 3:
                        System.out.println("Type task number to remove : ");
                        int Update_Index = Check_Int();
                        Update_Index = Update_Index - 1;

                        if (Tasks_LL.isEmpty()) {
                            System.out.print("\033[H\033[2J");
                            System.out.println("No");
                        } else {
                            Tasks_LL.remove(Update_Index);
                            System.out.print("\033[H\033[2J");
                            System.out.println("Yay");

                        }

                        break;

                    case 4:
                        scanner.nextLine();
                        System.out.print("\033[H\033[2J");
                        return;
                        
                    default:
                    
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
                System.out.print("\033[H\033[2J");
                TaskManager.Array_Menu();
            } else if (Menu_Choice == 2) {
                System.out.print("\033[H\033[2J");
                TaskManager.LinkedList_Menu();
            }
        }
    }
}