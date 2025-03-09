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
    Stack<Integer> Tasks_Array_Status = new Stack<>();
    LinkedList<String> Tasks_LL = new LinkedList<>();
    Stack<Integer> Tasks_LL_Status = new Stack<>();

    static Scanner scanner = new Scanner(System.in);

/*-------------------------------------------------------------------------------------------------------------------------------------*/

    // ==================== Method area :O ==================== //

    // Array Methods //

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
    public static void Clear_Console() {
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

    // Method for listing all tasks in the array and its completion status
    public static void Array_List(String[] Tasks_Arr, Stack<Integer> Tasks_Array_Status) {
        for (int i = 0; i < Tasks_Arr.length; i++) { 
            System.out.print((i+1) + ". " + Tasks_Arr[i]);
            
            // Stack usage. If a task's index is in the stack, it's marked as complete
            if (Tasks_Array_Status.contains(i)) {
                System.out.print(green + " (Finished)\n" + reset);
            }

            else {
                System.out.print(red + " (Unfinished)\n" + reset);

            }
        }
    }

    // Method for updating a task in the array
    public static void Array_Update(String[] Tasks_Arr, Stack<Integer> Tasks_Array_Status) {
        while (true) {
            System.out.print("Which task would you like to update? (Type task number, 0 to exit) : ");
            scanner.nextLine(); // Removes newline from previous input
            int Update_Index = Check_Int(); // Gets integer user input from Check_Int method
            
            if (Update_Index > Tasks_Arr.length || Update_Index < 0) {
                System.out.println(red + "Task not found." + reset);

            } else if (Update_Index == 0) {
                Clear_Console();
                break;

            } else {
                Update_Index = Update_Index - 1;
                System.out.print("Update with what task : ");
                scanner.nextLine(); // Removes newline from previous input
                String Update_Task = scanner.nextLine();
                
                // Removes the task's index if marked as complete in stack
                if (Tasks_Array_Status.contains(Update_Index)) {
                    Tasks_Array_Status.remove(Tasks_Array_Status.indexOf(Update_Index));
                }
                
                Clear_Console();
                System.out.println(yellow + "Successfully updated task " + Tasks_Arr[Update_Index] + " with " + Update_Task + "!" + reset);
                Tasks_Arr[Update_Index] = Update_Task;
                break;
            }
        }       
    }

    // Method for marking a task as complete or undoing a task
    public static void Array_StatusUpdate(String[] Tasks_Arr, Stack<Integer> Tasks_Array_Status) {
        while (true) {
            System.out.print("Which task would you like to mark as complete? (Type 0 to exit, 9 to undo): ");
            scanner.nextLine(); // Removes newline from previous input
            int Update_Index = Check_Int(); // Gets integer user input from Check_Int method

            if (Update_Index == 0) {
                Clear_Console();
                break;
            }
            
            else if (Update_Index == 9) {

                // Removes newest task's index from stack which effectively removes it completion status
                if (Tasks_Array_Status.isEmpty()) {
                    System.out.println(yellow + "No completed tasks found..." + reset);

                } else {
                    System.out.println(yellow + "Task " + Tasks_Array_Status.peek() + " has been undoed!" + reset);
                    Tasks_Array_Status.pop(); 
                }

            }

            // Error handling for out of bound index
            else if (Update_Index > Tasks_Arr.length || Update_Index < 0) {
                System.out.println(red + "Task not found." + reset);

            } 

            else {
                Update_Index = Update_Index - 1; // Arrays start with index 0
                
                // Adds task's index to stack to mark it as complete if it isn't in the stack
                if (Tasks_Array_Status.contains(Update_Index)){
                    System.out.println(yellow + "Task already completed!" + reset);
                } 
                else {
                    Clear_Console();
                    Tasks_Array_Status.push(Update_Index);
                    System.out.println(yellow + "Successfully updated task " + Tasks_Arr[Update_Index] + " as complete!" + reset);
                }
            }
        }
    }

    // Linked List Methods //

    public static void LinkedList_Listing(LinkedList<String> Tasks_LL, Stack<Integer> Tasks_LL_Status) {

        if (Tasks_LL.isEmpty()) {
            System.out.println(yellow + "Nothing in the list yet." + reset);
        } 

        else {
            for (int Update_Index = 0; Update_Index < Tasks_LL.size(); Update_Index++) {
                System.out.print((Update_Index + 1) + ". " + Tasks_LL.get(Update_Index));

                // Stack usage. If a task's index is in the stack, it's marked as  complete
                if (!Tasks_LL_Status.contains(Update_Index)) {
                    System.out.println(red + " (Unfinished)" + reset);

                } else {
                    System.out.println(green + " (Finished)" + reset);
                }
            }
        }
    }

    public static void LinkedList_Add(LinkedList<String> Tasks_LL) {
        System.out.print("Type task to add (0 to exit): ");
        scanner.nextLine(); // Removes newline from previous input
        String Update_Task = scanner.nextLine();

        if (Update_Task.equals("0")) {
            Clear_Console();
        }
                            
        else {
            Tasks_LL.add(Update_Task);
            Clear_Console();
            System.out.println(yellow + "Successfully added task " + Update_Task + "!" + reset);
        }
    }
    
    public static void LinkedList_Remove(LinkedList<String> Tasks_LL, Stack<Integer> Tasks_LL_Status) {

        while (true) {
            System.out.print("Type task number to remove (0 to exit): ");
            scanner.nextLine(); // Clears the newline from previous input
            int Update_Index = Check_Int(); // Gets integer user input
    
            if (Update_Index == 0) {
                Clear_Console();
                break;

            } else if (Tasks_LL.isEmpty()){
                Clear_Console();
                System.out.println(yellow + "Nothing in the list yet." + reset);
                break;

            // Error handling for out of bounds index
            } else if (Update_Index > Tasks_LL.size() || Update_Index < 0) {
                System.out.println(red + "Task not found." + reset);

            } else {
                Update_Index = Update_Index - 1;
    
                // Remove the task's index from the status stack if it exists
                if (Tasks_LL_Status.contains(Update_Index)) {
                    Tasks_LL_Status.remove(Tasks_LL_Status.indexOf(Update_Index));
                }
    
                Tasks_LL.remove(Update_Index);
    
                // Update all indices in Tasks_LL_Status that are greater than the removed index.
                for (int i = 0; i < Tasks_LL_Status.size(); i++) {
                    int currentIndex = Tasks_LL_Status.get(i);
                    if (currentIndex > Update_Index) {
                        Tasks_LL_Status.set(i, currentIndex - 1);
                    }
                }
    
                Clear_Console();
                System.out.println(yellow + "Removed task!" + reset);
                break;
            }
        } 
    }
    
    public static void LinkedList_UpdateStatus(LinkedList<String> Tasks_LL, Stack<Integer> Tasks_LL_Status) {

        while (true) {
            System.out.print("Which task would you like to mark as complete? (Type 0 to exit, 9 to undo): ");
            scanner.nextLine(); // Removes newline from previous input
            int Update_Index = Check_Int(); // Gets integer user input from Check_Int method

            if (Update_Index == 0) {
                Clear_Console();
                break;
            }
            
            // Removes newest task's index from stack which effectively removes it completion status
            else if (Update_Index == 9) {

                if (Tasks_LL_Status.isEmpty()) {
                    System.out.println(yellow + "No completed tasks found..." + reset);

                } else {
                    System.out.println(yellow + "Task " + Tasks_LL_Status.peek() + " has been undoed!" + reset);
                    Tasks_LL_Status.removeLast();
                }
            }

            // Error handling for out of bound index
            else if (Update_Index > Tasks_LL.size()|| Update_Index < 0) {
                System.out.println(red + "Task not found." + reset);

            } 

            else {
                Update_Index = Update_Index - 1; // Indexes start from 0 but my code wants input of task number 1 - n, so it needs to be subtracted by 1

                // Adds task 's index to the stack to mark it as complete if it isn't in the stack
                if (Tasks_LL_Status.contains(Update_Index)){
                    System.out.println(yellow + "Task already completed!" + reset);
                } 
                else {
                    Clear_Console();
                    Tasks_LL_Status.push(Update_Index);
                    System.out.println(yellow + "Successfully added task " + Tasks_LL.get(Update_Index) + " as complete!" + reset);
                }
            }
        }
    }

    // Menu Methods //

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
                        Clear_Console();
                        Array_List(Tasks_Arr, Tasks_Array_Status);
                        break;
                                       
                    case 2:
                        Array_Update(Tasks_Arr, Tasks_Array_Status);
                        break;

                    case 3:
                        Array_StatusUpdate(Tasks_Arr, Tasks_Array_Status);
                        break;

                    // Go back to main menu
                    case 4:
                        scanner.nextLine();
                        Clear_Console();
                        return;
                        
                    default:
                        Clear_Console();
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
                        Clear_Console();
                        LinkedList_Listing(Tasks_LL, Tasks_LL_Status);
                        break;
                                       
                    case 2:
                        LinkedList_Add(Tasks_LL);
                        break;

                    case 3:
                        LinkedList_Remove(Tasks_LL, Tasks_LL_Status);
                        break;

                    case 4:
                        LinkedList_UpdateStatus(Tasks_LL, Tasks_LL_Status);
                        break;

                    case 5:
                        Clear_Console();
                        return;

                    default:
                        Clear_Console();
                        System.out.println("Invalid choice");
                }
            }
        }

/*-------------------------------------------------------------------------------------------------------------------------------------*/

    // ==================== Main Program Area :O ==================== //
    public static void main(String[] args) throws Exception {

        DailyTaskManager TaskManager = new DailyTaskManager();
        Clear_Console();
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
                Clear_Console();
                TaskManager.Array_Menu();
            } else if (Menu_Choice == 2) {
                Clear_Console();
                TaskManager.LinkedList_Menu();
            } else if (Menu_Choice == 0) {
                scanner.close();
                System.out.print(green + "Bye bye" + reset);
                break;
            } else {
                Clear_Console();
                System.out.println(red + "Not a valid option!" + reset);
            }
        }
    }
}