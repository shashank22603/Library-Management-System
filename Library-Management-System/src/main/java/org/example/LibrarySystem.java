package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;

public class LibrarySystem extends Library {
    public static void main(String[] args) {
        Library library = new Library();
        Librarian librarian = new Librarian();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("Library Portal Initialized…");
        while (isRunning) {
            try {
                System.out.println("---------------------------------");
                System.out.println("1. Enter as a librarian");
                System.out.println("2. Enter as a member");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    while (true) {
                        // Librarian Menu
                        try {
                            System.out.println("Librarian Menu:");
                            System.out.println("1. Register a member");
                            System.out.println("2. Remove a member");
                            System.out.println("3. Add a book");
                            System.out.println("4. Remove a book");
                            System.out.println("5. View all books");
                            System.out.println("6. View all members with books and fines");
                            System.out.println("7. Exit as librarian");
                            System.out.print("Enter your choice: ");
                            int librarianChoice = scanner.nextInt();

                            scanner.nextLine();

                            if (librarianChoice == 3) {
                                // Add a book
                                System.out.print("Enter book title: ");
                                String title = scanner.nextLine();
                                System.out.print("Enter book author: ");
                                String author = scanner.nextLine();
                                System.out.print("Enter Total No. of Copies: ");
                                String totalcopies = scanner.nextLine();
                                librarian.addBook(title, author, Integer.parseInt(totalcopies));
                            } else if (librarianChoice == 4) {
                                // Remove a book
                                System.out.print("Enter book title: ");
                                String title = scanner.nextLine();
                                System.out.print("Enter book id: ");
                                int id = scanner.nextInt();
                                librarian.removeBook(title, id);
                            } else if (librarianChoice == 5) {
                                // View all books
                                librarian.viewAllBooks();
                            } else if (librarianChoice == 6) {
                                // View all members
                                librarian.viewAllMembersWithBooksAndFines();
                            } else if (librarianChoice == 1) {
                                // Register a member
                                System.out.print("Enter member name: ");
                                String memberName = scanner.nextLine();
                                System.out.print("Enter member phone number: ");
                                String phoneNumber = scanner.nextLine();
                                if (!phoneNumber.matches("\\d{10}")) {
                                    System.out.println("Invalid phone number format. Phone number should be a 10-digit numeric value.");
                                    continue;
                                }
                                System.out.print("Enter member age: ");
                                String age = scanner.nextLine();
                                librarian.registerMember(memberName, phoneNumber, Integer.parseInt(age));
                            } else if (librarianChoice == 2) {
                                // Remove a member
                                System.out.print("Enter member name: ");
                                String memberName = scanner.nextLine();
                                System.out.print("Enter member phone number: ");
                                String phoneNumber = scanner.nextLine();
                                if (!phoneNumber.matches("\\d{10}")) {
                                    System.out.println("Invalid phone number format. Phone number should be a 10-digit numeric value.");
                                    continue;
                                }
                                librarian.removeMember(memberName, phoneNumber);
                            } else if (librarianChoice == 7) {
                                // Exit as librarian
                                System.out.println("Exiting librarian mode.");
                                break;
                            } else {
                                System.out.println("Invalid choice. Please enter a valid option.");
                            }
                        }
                        catch (Exception e) {
                            System.out.println("Invalid input. Please try again.");
                            scanner.nextLine(); // Consume the invalid input
                        }

                    }
                }else if (choice == 2) {
                    System.out.print("Enter your name: ");
                    String memberName = scanner.nextLine();
                    System.out.print("Enter your phone number: ");
                    String phoneNumber = scanner.nextLine();
                    if (!phoneNumber.matches("\\d{10}")) {
                        System.out.println("Invalid phone number format. Phone number should be a 10-digit numeric value.");
                        continue;
                    }
                    Member loggedInMember = library.loginMember(memberName, phoneNumber);

                    if (loggedInMember != null) {
                        // Successful login
                        System.out.println("Welcome " + loggedInMember.getName() + ". Member ID: " + loggedInMember.getMemberID());

                        Member member = new Member(phoneNumber, memberName, loggedInMember.age);
                        while (true) {
                            // Member Menu
                            try {
                                System.out.println("Member Menu:");
                                System.out.println("1. List available books");
                                System.out.println("2. List my books");
                                System.out.println("3. Issue a book");
                                System.out.println("4. Return a book");
                                System.out.println("5. Pay fines");
                                System.out.println("6. Exit as member");
                                System.out.print("Enter your choice: ");

                                int memberChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (memberChoice == 1) {
                                    member.listAvailableBooks();
                                } else if (memberChoice == 2) {
                                    member.listMyBooks();
                                }else if (memberChoice == 3) {
                                    // Issue a book
                                    System.out.print("Enter book id: ");
                                    int id = scanner.nextInt();
                                    scanner.nextLine(); // Consume the newline character
                                    System.out.print("Enter book name: ");
                                    String title = scanner.nextLine();
                                    member.issueBook(member, title, id);
                                } else if (memberChoice == 4) {
                                    // Return a book
                                    System.out.print("Enter book id: ");
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    //Books book = new Books(id,title,author);
                                    member.returnBook(member, id);
                                } else if (memberChoice == 5) {
                                    System.out.print("Your fine amount is Rs." + member.getPenaltyAmount());
                                    member.payFine(member.getPenaltyAmount());
                                } else if (memberChoice == 6) {
                                    // Exit as member
                                    System.out.println("Exiting member mode.");
                                    break;
                                } else {
                                    System.out.println("Invalid choice. Please enter a valid option.");
                                }
                            }
                            catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                                scanner.nextLine();
                            }
                        }
                    } else {
                        // Login failed
                        System.out.println("Member not registered or invalid credentials.");
                    }
                } else if (choice == 3) {
                    // Exit
                    isRunning = false;
                    System.out.println("Exiting the Library Portal. Goodbye!");
                } else {
                    System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        scanner.close();
    }
}
