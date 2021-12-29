import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RealEstate r = new RealEstate();
        int choice;
        do {
            System.out.println("If you want to sign up - press 1.");
            System.out.println("If you want to log in - press 2.");
            System.out.println("If you want to finish - press 3.");
            choice = scanner.nextInt();
            if (choice == 1){
                r.createUser();
            } else if (choice == 2){
                r.userLogIn();
            } else if (choice != 3){
                System.out.println("Invalid choice");
            }
        } while (choice != 3);
    }
}
