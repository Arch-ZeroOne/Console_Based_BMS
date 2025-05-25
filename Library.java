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


        try {
            File file = new File("books.txt");
            FileWriter writer = new FileWriter(file, true);
            Scanner reader = new Scanner(file);
            String accession_details = null, id = null;
            Library library = new Library();
            ArrayList<String> container = new ArrayList<>();
            boolean existing = false;
            int book_stocks = 0, count_to_edit = 0, count = 0;


            System.out.println();
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
            System.out.println();


            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] split = data.split(",");
                count++;

                if (bname.equals(split[1])) {
                    existing = true;
                    book_stocks = Integer.parseInt(split[4]);
                    count_to_edit = count;
                    id = split[0];

                }

                container.add(data);

            }


            //writes to database
            if (existing) {
                FileWriter clear_db = new FileWriter(file);
                for (int a = 0; a < container.size(); a++) {
                    if (a + 1 == count_to_edit) {
                        clear_db.write(id + "," + bname + "," + bauthor + "," + bpublisher + "," + (book_stocks + Integer.parseInt(quan)) + "," + pyear + ",Available" + "\n");

                    } else {
                        clear_db.write(container.get(a) + "\n");
                    }
                }
                clear_db.close();

            } else {

                writer.write(library.getBookId() + "," + bname + "," + bauthor + "," + bpublisher + "," + quan + "," + pyear + ",Available" + "\n");

            }

            //stores accession details
            accession_details = bname + "," + bauthor + "," + bpublisher + "," + quan + "," + pyear + ",Available" + "\n";
            //updates accession details
            updateAccession(accession_details, false);


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
        String details_to_edit = null, title_to_edit = null;


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
                    newDetails = split[0] + "," + bname + "," + bauthor + "," + bpublisher + "," + split[4] + "," + pyear + "," + split[6];
                    instanceToEdit = count;
                    found = true;
                    title_to_edit = split[1];
                    details_to_edit = bname + "," + bauthor + "," + bpublisher + "," + split[4] + "," + pyear + "," + split[6] + "," + title_to_edit;


                }

                //mag store sa mga copy
                tempStorage.add(data);


            }
            if (details_to_edit != null) {
                updateAccession(details_to_edit, true);
                System.out.println(details_to_edit);

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
                System.out.printf("| %-10s | %-40s | %-40s | %-40s | %-20s | %-17s | %-15s |  \n",
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
            e.printStackTrace();
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

    static void updateAccession(String details, boolean update) {
        String split[] = details.split(",");
        ArrayList<String> container = new ArrayList<>();
        boolean existing = false, found = false;
        int count = 0, posToEdit = 0;

        try {
            File file = new File("accession.txt");
            Scanner reader = new Scanner(file);


            if (!update) {
                FileWriter writer = new FileWriter(file, true);
                //reads file for filtering of same book title
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    String sep[] = data.split(",");


                    //if book title exists we count its instance to continue the count for id
                    if (sep[0].contains(split[0])) {
                        existing = true;
                        count++;


                    }
                }

                if (existing) {

                    for (int a = 0; a < Integer.parseInt(split[3]); a++) {
                        writer.write(split[0] + "-" + split[4] + "-" + ((a + 1) + count) + "," + split[1] + "," + split[5]);


                    }

                } else {
                    for (int a = 0; a < Integer.parseInt(split[3]); a++) {
                        writer.write(split[0] + "-" + split[4] + "-" + (a + 1) + "," + split[1] + "," + split[5]);


                    }

                }
                writer.close();

            } else {
                try {
                    while (reader.hasNextLine()) {
                        String data = reader.nextLine();
                        String sep[] = data.split(",");
                        count++;

                        System.out.println(sep[0]);
                        System.out.println(split[6]);


                        if (sep[0].contains(split[6]) && !found) {

                            posToEdit = count;
                            found = true;


                        }

                        container.add(data);


                    }

                    FileWriter writer = new FileWriter(file);
                    int index = 0;
                    for (int a = 0; a < container.size(); a++) {
                        if (a + 1 == posToEdit) {
                            for (int b = 0; b < Integer.parseInt(split[3]); b++) {
                                writer.write(split[0] + "-" + split[4] + "-" + (b + 1) + "," + split[1] + "," + split[5] + "\n");
                                index++;

                            }
                        } else {
                            if (index != 0) {

                                index++;

                            } else {
                                writer.write(container.get(a) + "\n");

                            }

                        }
                    }
                    writer.close();

                } catch (Exception e) {
                    e.printStackTrace();

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}