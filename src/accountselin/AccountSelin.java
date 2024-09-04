
package accountselin;

import java.util.Scanner;

public class AccountSelin {
    private static final Scanner sc = new Scanner(System.in);
    private static final Accounts[] acs = new Accounts[100];


    public static void main(String[] args) {
        getAccount();
    }

    public static void getAccount() {
        System.out.print("Add number of users: ");
        int user = sc.nextInt();
        sc.nextLine(); 

        for (int i = 0; i < user; i++) {
            System.out.println("\nEnter details of User " + (i + 1));
            System.out.print("ID: ");
            int ID = sc.nextInt();
            sc.nextLine(); // Consume newline
            System.out.print("First name: ");
            String fName = sc.nextLine();
            System.out.print("Last name: ");
            String lName = sc.nextLine();
            System.out.print("Email: ");
            String Eadd = sc.nextLine();
            System.out.print("Username: ");
            String usern = sc.nextLine();

            System.out.println("\nPassword criteria:"
                    + "\n1. Must be above 8 characters"
                    + "\n2. Must have at least 1 upper & 1 lower case letter"
                    + "\n3. Must have at least 1 number"
                    + "\n4. Must have at least 1 special character"
                    + "\n5. Must not have common passwords like 'admin', 'password', or '1234'");

            System.out.print("\nPassword: ");
            String passw = sc.nextLine();

            while (!passwordVerify(passw)) {
                System.out.print("\nPassword: ");
                passw = sc.nextLine();
            }

            if (duplicateVerify(ID, Eadd, usern, i)) {
                i--;
                continue;
            }

            acs[i] = new Accounts();
            acs[i].addAccounts(ID, fName, lName, Eadd, usern, passw);
        }

        System.out.printf("\n\n%-5s %-10s %-10s %-20s %-10s %-10s\n", "ID", "First Name", "Last Name", "Email", "Username", "Password");

        for (int i = 0; i < user; i++) {
            acs[i].viewAccounts();
        }
    }

    private static boolean duplicateVerify(int id, String email, String user, int index) {
        for (int x = 0; x < index; x++) {
            if (id == acs[x].aid) {
                System.out.println("\nInput invalid, must not have a duplicated ID.");
                return true;
            } else if (email.equals(acs[x].email)) {
                System.out.println("\nInput invalid, must not have a duplicated Email.");
                return true;
            } else if (user.equals(acs[x].user)) {
                System.out.println("\nInput invalid, must not have a duplicated Username.");
                return true;
            }
        }
        return false;
    }

    private static boolean passwordVerify(String password) {
        if (password.length() <= 8) {
            System.out.println("\nPassword is invalid, password must be above 8 characters");
            return false;
        }

        if (password.equals("admin") || password.equals("password") || password.equals("1234")) {
            System.out.println("\nPassword is invalid, password must not be one of the common passwords like 'admin', 'password', and '1234'");
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialchar = false;

        for (char z : password.toCharArray()) {
            if (Character.isLowerCase(z)) {
                hasLowercase = true;
            } else if (Character.isUpperCase(z)) {
                hasUppercase = true;
            } else if (Character.isDigit(z)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(z)) {
                hasSpecialchar = true;
            }
        }

        if (!(hasUppercase && hasLowercase)) {
            System.out.println("\nPassword is invalid, must have 1 upper and 1 lower case letter");
            return false;
        } else if (!hasDigit) {
            System.out.println("\nPassword is invalid, must have at least 1 number");
            return false;
        } else if (!hasSpecialchar) {
            System.out.println("\nPassword is invalid, must have at least 1 special character");
            return false;
        }
        return true;
    }
}
