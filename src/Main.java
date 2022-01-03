import java.util.Scanner;

public class Main {
    public static final int SIGN_UP = 1;
    public static final int LOG_IN = 2;
    public static final int FINISH = 3;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RealEstate r = new RealEstate();
        int choice;
        do {
            System.out.println("If you want to sign up - press 1.");
            System.out.println("If you want to log in - press 2.");
            System.out.println("If you want to finish - press 3.");
            choice = scanner.nextInt();
            if (choice == SIGN_UP){
                r.createUser();
            } else if (choice == LOG_IN){
                r.userLogIn();
            } else if (choice != FINISH){
                System.out.println("Invalid choice");
            }
        } while (choice != FINISH);
    }
}
