import java.util.Scanner;

import services.DriverService;
import services.LoginService;
import services.PassengerService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== RIDE SHARE APPLICATION =====");

            System.out.println("1. Login");
            System.out.println("2. Register Passenger");
            System.out.println("3. Register Driver");
            System.out.println("4. Exit");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    LoginService.login();
                    break;

                case 2:

                    PassengerService.registerPassenger();
                    break;

                case 3:

                    DriverService.registerDriver();
                    break;

                case 4:

                    System.out.println("Thank You!");
                    System.exit(0);

                default:

                    System.out.println("Invalid Choice");
            }

        }

    }
}