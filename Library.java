import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    //Global scope variables
    static Scanner scan = new Scanner(System.in);

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("╔══════════════════════════════════╗");
            System.out.println("║                                  ║");
            System.out.println("║    Welcome to Elton's Library    ║");
            System.out.println("║                                  ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║                                  ║");
            System.out.println("║      Please Choose an Action:    ║");
            System.out.println("║                                  ║");
            System.out.println("║       1 - Log in                 ║");
            System.out.println("║       2 - Register               ║");
            System.out.println("║       3 - Exit                   ║");
            System.out.println("║                                  ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.print("Command:");
            int command = sc.nextInt();

            switch (command) {
                case 1:
                    logInForm();
                    break;
                case 2:
                    registrationForm();
                    break;
                case 3:
                    System.out.println("\nThank you for stopping by :)\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Command Please Choose from the actions available");
                    break;
            }

        }


    }

    static void logInForm() {
        System.out.println();
        System.out.println(" ══════════════════════════════════");
        System.out.println("║                                  ║");
        System.out.println("║        W E L C O M E             ║");
        System.out.println("║          L O G I N               ║");
        System.out.println("║                                  ║");
        System.out.println(" ══════════════════════════════════");
        System.out.print("Username:");
        String username = scan.nextLine();
        System.out.print("Password:");
        String password = scan.nextLine();
        validateUser(username, password);


    }


    static void validateUser(String username, String password) {
        boolean found = false;
        boolean isAdmin = false;
        try {

            File file = new File("studentsAccounts.txt");
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String account = fileReader.nextLine();
                String splitAcc[] = account.split(",");


                if (username.trim().equals(splitAcc[1].trim()) && password.trim().equals(splitAcc[2].trim())) {
                    found = true;
                    if (splitAcc[0].trim().equals("Admin")) {
                        isAdmin = true;
                    }
                    break;
                }

            }

            if (isAdmin && found) {
                System.out.println();
                System.out.println(" ══════════════════════════════════ ");
                System.out.println("║                                  ║");
                System.out.println("║        W E L C O M E             ║");
                System.out.println("║            Admin                 ║");
                System.out.println("║                                  ║");
                System.out.println(" ══════════════════════════════════ ");
                System.out.println();

                adminMenu();

            } else if (!isAdmin && found) {

                studentMenu();

            }

            fileReader.close();


        } catch (Exception e) {
            System.out.println("Error at Validate User");

        }

    }

    static void registrationForm() {


    }


    static void adminMenu() {
        boolean logOut = false;
        while (true) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("|      📚 Book Menu System      |");
            System.out.println("=================================");
            System.out.println("| 1 - Add Book                  |");
            System.out.println("| 2 - Update Book               |");
            System.out.println("| 3 - Delete Book               |");
            System.out.println("| 4 - Get Book                  |");
            System.out.println("| 5 - Search Book               |");
            System.out.println("| 6 - View Borrow History       |");
            System.out.println("| 7 - Log Out                   |");
            System.out.println("=================================");
            System.out.print("Enter your choice (1-6): ");
            int choice = scan.nextInt();
            scan.nextLine();


            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    updateBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    break;
                case 5:
                    searchBook();
                    break;
                case 6:
                    break;
                case 7:
                    logOut = true;
                    break;


            }
            if (logOut) {

                System.out.println();
                System.out.println("╭────────────────────────────────────────╮");
                System.out.println("│                                        │");
                System.out.println("│      (^_^) Logout successfully         │");
                System.out.println("│                                        │");
                System.out.println("╰────────────────────────────────────────╯");
                System.out.println();


                break;

            }
        }


    }

    static void studentMenu() {
        System.out.println("Student menu");

    }


    static void addBook() {
        //calls the table status if data exists
        tableStatus();

        System.out.println();
        ArrayList<String> container = new ArrayList<>();
        try {
            File file = new File("books.txt");
            FileWriter writer = new FileWriter(file, true);

            Library library = new Library();

            System.out.print("Book title:");
            String bname = scan.nextLine();
            System.out.print("Book author:");
            String bauthor = scan.nextLine();
            System.out.print("Book publisher:");
            String bpublisher = scan.nextLine();
            System.out.print("Quantity:");
            String quan = scan.nextLine();
            System.out.print("Publication year:");
            String pyear = scan.nextLine();


            writer.write(library.getBookId() + "," + bname + "," + bauthor + "," + bpublisher + "," + quan + "," + pyear + ",Available" + "\n");
            writer.close();
            System.out.println();
            System.out.println(" ══════════════════════════════════ ");
            System.out.println("║                                  ║");
            System.out.println("║      Book successfully added     ║");
            System.out.println("║                                  ║");
            System.out.println(" ══════════════════════════════════ ");
            System.out.println();
            tableStatus();


        } catch (Exception e) {

        }

    }


    static void deleteBook() {
        File file = new File("books.txt");
        ArrayList<String> tempStorage = new ArrayList<>();
        String newDetails = null;
        boolean found = false, confirm = false;
        int count = 0, instanceToEdit = 0;


        System.out.print("Enter Book ID:");
        String bookId = scan.nextLine();

        if (file.length() == 0) {
            System.out.println();
            System.out.println("╭────────────────────────────────────────╮");
            System.out.println("│                                        │");
            System.out.println("│   ℹ️  No available books yet           │");
            System.out.println("│                                        │");
            System.out.println("╰────────────────────────────────────────╯");
            System.out.println();

            return;
        }


        try {

            Scanner search = new Scanner(file);

            while (search.hasNextLine()) {
                String data = search.nextLine();
                String split[] = data.split(",");
                String id = split[0];
                count++;

                if (bookId.equals(id) && split[6].equals("Available")) {
                    System.out.println();
                    System.out.println(" ╔═════════════════════════════════╗");
                    System.out.println("║                                 ║");
                    System.out.println("║      *** ID MATCHED ***          ║");
                    System.out.println("║                                 ║");
                    System.out.println(" ╚═════════════════════════════════╝");
                    System.out.println();
                    System.out.printf("%-18s : %s%n", "BOOK ID", split[0]);
                    System.out.printf("%-18s : %s%n", "Title", split[1]);
                    System.out.printf("%-18s : %s%n", "Author", split[2]);
                    System.out.printf("%-18s : %s%n", "Publisher", split[3]);
                    System.out.printf("%-18s : %s%n", "Quantity", split[4]);
                    System.out.printf("%-18s : %s%n", "Publication Year", split[5]);
                    System.out.printf("%-18s : %s%n", "Status", split[6]);
                    System.out.println();
                    System.out.println(" ╔════════════════════════════════════════════════╗");
                    System.out.println("║                                                ║");
                    System.out.println("║     ⚠️  Confirm Deletion                       ║");
                    System.out.println("║     You cannot revert this action.             ║");
                    System.out.println("║                                                ║");
                    System.out.println(" ╚════════════════════════════════════════════════╝");
                    System.out.println();
                    System.out.println(" 1 - Yes ");
                    System.out.println(" 2 - NO ");
                    System.out.println();
                    System.out.print("Command:");
                    int command = scan.nextInt();
                    scan.nextLine();

                    switch (command) {
                        case 1:
                            instanceToEdit = count;
                            newDetails = split[0] + "," + split[1] + "," + split[2] + "," + split[3] + "," + split[4] + "," + split[5] + "," + "Unavailable";
                            found = true;
                            confirm = true;
                            break;
                        case 2:
                            confirm = false;
                            break;

                    }


                }

                tempStorage.add(data);


            }


            if (found && confirm) {
                FileWriter writer = new FileWriter(file);

                for (int a = 0; a < tempStorage.size(); a++) {
                    if (a + 1 == instanceToEdit) {
                        writer.write(newDetails + "\n");

                    } else {
                        writer.write(tempStorage.get(a) + "\n");
                    }
                }

                System.out.println();
                System.out.println("╭────────────────────────────────────────╮");
                System.out.println("│                                        │");
                System.out.println("│      (^_^) Deletion successful!         │");
                System.out.println("│                                        │");
                System.out.println("╰────────────────────────────────────────╯");
                System.out.println();

                writer.close();


            } else if (!confirm) {
                System.out.println();
                System.out.println("╭────────────────────────────────────────────╮");
                System.out.println("│                                            │");
                System.out.println("│    (－‸ლ) Deletion canceled                │");
                System.out.println("│                                            │");
                System.out.println("╰────────────────────────────────────────────╯");
                System.out.println();

                return;

            } else {
                System.out.println();
                System.out.println("╭────────────────────────────────────────╮");
                System.out.println("│                                        │");
                System.out.println("│      (╥_╥) Book not found              │");
                System.out.println("│                                        │");
                System.out.println("╰────────────────────────────────────────╯");
                System.out.println();


            }


        } catch (Exception e) {
            System.out.println("Error at delete book");
        }
    }

    static void updateBook() {
        File file = new File("books.txt");
        ArrayList<String> tempStorage = new ArrayList<>();
        String newDetails = null;
        boolean found = false;
        int count = 0, instanceToEdit = 0;
        Library library = new Library();
        System.out.print("Enter Book ID:");
        String bookId = scan.nextLine();

        if (file.length() == 0) {
            System.out.println();
            System.out.println("╭────────────────────────────────────────╮");
            System.out.println("│                                        │");
            System.out.println("│   ℹ️  No available books yet           │");
            System.out.println("│                                        │");
            System.out.println("╰────────────────────────────────────────╯");
            System.out.println();

            return;
        }


        try {

            Scanner search = new Scanner(file);

            while (search.hasNextLine()) {
                String data = search.nextLine();
                String split[] = data.split(",");
                String id = split[0];
                count++;

                if (bookId.equals(id)) {
                    System.out.println();
                    System.out.println(" ╔═════════════════════════════════╗");
                    System.out.println("║                                 ║");
                    System.out.println("║      *** ID MATCHED ***          ║");
                    System.out.println("║                                 ║");
                    System.out.println(" ╚═════════════════════════════════╝");
                    System.out.println();
                    System.out.printf("%-18s : %s%n", "BOOK ID", split[0]);
                    System.out.printf("%-18s : %s%n", "Title", split[1]);
                    System.out.printf("%-18s : %s%n", "Author", split[2]);
                    System.out.printf("%-18s : %s%n", "Publisher", split[3]);
                    System.out.printf("%-18s : %s%n", "Quantity", split[4]);
                    System.out.printf("%-18s : %s%n", "Publication Year", split[5]);
                    System.out.printf("%-18s : %s%n", "Status", split[6]);
                    System.out.println();
                    System.out.println(" ╔═════════════════════════════════╗");
                    System.out.println("║                                 ║");
                    System.out.println("║       *** Enter new details ***  ║");
                    System.out.println("║                                 ║");
                    System.out.println(" ╚═════════════════════════════════╝");
                    System.out.print("New Book title:");
                    String bname = scan.nextLine();
                    System.out.print("New Book author:");
                    String bauthor = scan.nextLine();
                    System.out.print("New Book publisher:");
                    String bpublisher = scan.nextLine();
                    System.out.print("New Publication year:");
                    String pyear = scan.nextLine();
                    newDetails = split[0] + "," + bname + "," + bauthor + "," + bpublisher + "," + pyear + "," + split[5];
                    instanceToEdit = count;
                    found = true;


                }

                tempStorage.add(data);


            }
            if (found) {
                FileWriter writer = new FileWriter(file);
                for (int a = 0; a < tempStorage.size(); a++) {
                    if (a + 1 == instanceToEdit) {
                        writer.write(newDetails + "\n");

                    } else {
                        writer.write(tempStorage.get(a) + "\n");
                    }
                }
                System.out.println();
                System.out.println("╭────────────────────────────────────────╮");
                System.out.println("│                                        │");
                System.out.println("│      ✅ Edit successful!               │");
                System.out.println("│                                        │");
                System.out.println("╰────────────────────────────────────────╯");
                System.out.println();
                writer.close();

            } else {
                System.out.println();
                System.out.println("╭────────────────────────────────────────╮");
                System.out.println("│                                        │");
                System.out.println("│      (╥_╥) Book not found              │");
                System.out.println("│                                        │");
                System.out.println("╰────────────────────────────────────────╯");
                System.out.println();


            }


            search.close();


        } catch (Exception e) {
            System.out.println("Error at update book");
        }

    }

    static void getBooks() {

    }

    static void searchBook() {
        System.out.print("Enter Book ID:");
        String bookId = scan.nextLine();
        boolean found = false;

        try {
            File file = new File("books.txt");
            Scanner search = new Scanner(file);

            while (search.hasNextLine()) {
                String data = search.nextLine();
                String split[] = data.split(",");
                String id = split[0];


                if (bookId.equals(id) && split[6].equals("Available")) {
                    System.out.println();
                    System.out.println(" ╔═════════════════════════════════╗");
                    System.out.println("║                                 ║");
                    System.out.println("║      *** ID MATCHED ***          ║");
                    System.out.println("║                                 ║");
                    System.out.println(" ╚═════════════════════════════════╝");
                    System.out.println();
                    System.out.printf("%-18s : %s%n", "BOOK ID", split[0]);
                    System.out.printf("%-18s : %s%n", "Title", split[1]);
                    System.out.printf("%-18s : %s%n", "Author", split[2]);
                    System.out.printf("%-18s : %s%n", "Publisher", split[3]);
                    System.out.printf("%-18s : %s%n", "Quantity", split[4]);
                    System.out.printf("%-18s : %s%n", "Publication Year", split[5]);
                    System.out.printf("%-18s : %s%n", "Status", split[6]);
                    System.out.println();
                    found = true;
                    break;

                }
            }

            if (!found) {
                System.out.println();
                System.out.println("╭────────────────────────────────────────╮");
                System.out.println("│                                        │");
                System.out.println("│      (╥_╥) Book not found              │");
                System.out.println("│                                        │");
                System.out.println("╰────────────────────────────────────────╯");
                System.out.println();


            }

        } catch (Exception e) {
            System.out.println("Error at search function");


        }

    }


    static void tableStatus() {

        try {
            File file = new File("books.txt");
            Scanner fileReader = new Scanner(file);
            if (file.length() != 0) {
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\t\t ══════════════════════════════════ ");
                System.out.println("\t\t\t\t\t\t\t\t\t║                                  ║");
                System.out.println("\t\t\t\t\t\t\t\t\t║         Available Books          ║");
                System.out.println("\t\t\t\t\t\t\t\t\t║                                  ║");
                System.out.println("\t\t\t\t\t\t\t\t\t ══════════════════════════════════ ");
                System.out.println();
                System.out.println("============================================================================================================================================================================================================");
                System.out.printf("| %-10s | %-40s | %-40s | %-40s | %-20s | %-17s | %-15s |\n",
                        "Book ID", "Title", "Author", "Publisher", "Quantity", "Publication Year", "Status");
                System.out.println("============================================================================================================================================================================================================");


                while (fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
                    String split[] = data.split(",");
                    if (split[6].equals("Available")) {
                        System.out.printf("| %-10s | %-40s | %-40s | %-40s | %-20s | %-17s | %-15s |\n============================================================================================================================================================================================================\n",
                                split[0], split[1], split[2], split[3], split[4], split[5], split[6]);

                    }

                }

            } else {
                System.out.println();
                System.out.println("╭────────────────────────────────────────╮");
                System.out.println("│                                        │");
                System.out.println("│   ℹ️  No available books yet           │");
                System.out.println("│                                        │");
                System.out.println("╰────────────────────────────────────────╯");
                System.out.println();

            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at tableStatus");
        }

        System.out.println();

    }

    public int getBookId() {
        int count = 0;

        try {

            File file = new File("books.txt");
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String account = fileReader.nextLine();
                count++;
            }

            fileReader.close();


        } catch (Exception e) {
            System.out.println("Error at getBookId User");

        }


        return count + 1;


    }


}