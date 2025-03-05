import java.util.Scanner;

public class DailyTaskManager {
    public static void main(String[] args) throws Exception {

/*-------------------------------------------------------------------------------------------------------------------------------------*/

        // ==================== Universal Variable Defining Area :O ==================== //

        String[] Tasks = {"Eat", "Sleep", "Play", "Sleep2", "Rest"};
        String Update_Task = "Placeholder";

        int Menu_Choice;
        int Update_Index = 0;
        int User_Choice;

        boolean Run = true;
        Scanner scanner = new Scanner(System.in);

/*-------------------------------------------------------------------------------------------------------------------------------------*/

        // ==================== Main Program Area :))) ==================== //

        System.out.print("\033[H\033[2J"); // Clears the console

        while (Run == true) {
            System.out.print("""
==============================
Which menu do you want to see?
1. Array
2. Linked List
============================== 
Please enter :  """);
            Menu_Choice = scanner.nextInt();
            if (Menu_Choice == 1) {
                System.out.println("""
================================
What would you like to do today?
1. Check all tasks
2. Update a task
3. Add a task
4. Back
================================
                        """);
                System.out.print("Enter your choice: ");
                User_Choice = scanner.nextInt();
                switch (User_Choice) {
                    case 1:
                        System.out.print("\033[H\033[2J");
                        for (int i = 0; i < Tasks.length; i++) {
                            System.out.println((i+1) + ". " + Tasks[i]);
                        }
                        break;
                        
                    case 2:
                        System.out.print("Which task would you like to update? (Type the index) : ");
                        Update_Index = scanner.nextInt() + 1;
                        System.out.print("Update with what task : ");
                        scanner.nextLine();
                        Update_Task = scanner.nextLine();
                        Tasks[Update_Index] = Update_Task;
                        System.out.print("\033[H\033[2J");
                        System.out.println("Succesfully updated!");
                        break;

                    case 3:
                        
                        break;

                    case 4:
                        break;
                        
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }
}