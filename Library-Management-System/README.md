# Library Management System (Java)

## Code Structure

The code is organized into several classes, each with a specific role:

- **Library**: 
 Manages the list of books and members and provides methods for member login.
- It is the parent class for every classes that have made
- it contains two list books and members
- books have only Books class elements elements
- Members have only member class elements
- It contains only one method that is login member to verify that a member who try to access library management system is registered member or not to verify a member it matches its name and phone number with the already existing members in the members list if they matches with any one of them it returns member element from the members list


- **Member**: Represents a library member and handles member-specific operations.
- Member contains all the data of a member like phone number name age member Id penalty amount all the borrowed books by him and the time on which they borrowed the book
- It has methods getPhoneNumber that returns the phone number of the member and is of string data type.
- getName returns name of a member and is of string data type.
- getMember Id returns member Id of the member and his also string data type.
- getBorrowedBook1 returns object of books class 
- getBorrowedBook2 returns object of books class
- getBorrowedBook1 set the BorrowedBook1
- getBorrowedBook2 set the BorrowedBook2
- getPenaltyAmount returns penalty amount and is of double data type 
- addpenalty takes one argument that is amount and set the penalty amount to the amount which is given in its argument
- listAvailableBooks shows all the books available in the library it does not include total books which are in the in the library it only shows available books.
- issueBook method take two argument one is object of member and second is object of book it first check whether the two given argument Are not null, if yes it shows error invalid member or book and close if they are correct it checks for any remaining Dues of the member if member has not cleared any previous dues then it shows message you have remaining fines to pay please clear your dues before borrowing a book then it checks if member has reached the Borrowing limits (that is two) if member reachces the limits it shows error member is already borrowed the maximum number of books 
- It also check whether a member is borrowing a same book again then it shows member has already borrowed this book if everything goes well it set the borrowed book one or two with their time of borrowing and it also decrease the available copies of the respected books and shows message
- returnBook method takes two argument (member and book) it check if the given argument are not null if they are it shows error invalid member or book if their is no error it goes ahead and check for member has borrowed that book or not if member has not borrowed the book it shows member is not borrowed this book and Returns if everything goes well then it calculate the elapsed time for which the member is borrowed the book if the elapsed time is less than 10 seconds (as I have taken 1 second is equal to one day ) so if a member returns the book within 10 days limit it accepts the book and shows a message if elapsed time is more than 10 second it  adds penalty to the member and calculates the fine amount (also have taken the fine amount for every single day is 3 rupees after return time is over) then it set borrowed book one or two whatever is to null and also increase the available copies of that book in books.
- payFine method only takes a double data type which is the amount which the member has to pay if amount is less than zero (means negative) it shows invalid fine for amount error if the penalty for the member is zero then it shows no finds to pay if there is some penalty for the member it shows the message the amount to pay in rupees , if a member pas the fine it set getPenaltyAmount to zero.
 
- **Books**: Represents a book in the library and tracks book-related operations.
- Books contain bookID, title, author, totalCopies and availableCopies only it has some methods like getBookID, getTitle, getAuthor, getTotalCopies, getAvailableCopies,  decreaseAvailableCopies, increaseAvailableCopies.
- **Librarian**: Handles librarian-specific operations like member registration and book management.
1.**Add Book**: Allows librarians to add a new book to the library. They can input book details such as title, author, and total copies.
2. **Remove Book**: Librarians can remove a book from the library by providing the book's ID.
3. **Register Member**: Librarians can add new members to the library by inputting member details, including name, age, and a unique phone number.
4. **Remove Member**: Librarians can remove a member from the library by specifying their ID or phone number.
5. **Enter as a Member**: Members can log in using their name and phone number, granting access to member-specific actions.
6. **Issue Book**: Members can borrow books from the library if they have no pending fines. They can choose from available books by book ID.
7. **Return Book**: Members can return borrowed books, and if they are returned within 10 days, no fines are applied.
8. **List Books**: Provides a list of all available books in the library.
9. **List Members**: Displays a list of all registered library members.
10. **Calculate Fine**: Calculates and displays the fine amount for a book returned after the due date (10 days).
11. **Exit**: Terminates the application.

- **idGen**: Generates unique member IDs. 
- Uses an infinite loop to generate member IDs until a unique one is found.
- Generates a random 4-digit integer.
- Formats the random integer as a 4-digit string with leading zeros to ensure consistency.
- Checks if the generated member ID is already in the `usedMemberIDs` list.
- If the member ID is not in the list (i.e., it's unique), it adds it to the `usedMemberIDs` list and returns it as the result.
- If the member ID is already in the list (i.e., not unique), it continues to generate another random member ID.
    #### Functionality Summary
- The primary functionality of this class is to generate unique member IDs.
- It ensures uniqueness by maintaining a list of used member IDs and checking each generated ID against this list.
- The `generateUniqueMemberID` method can be called by other parts of the program to obtain a unique member ID.

- **LibrarySystem**: The main class that contains the application's entry point and user interfaces for librarians and members.

## Menu and User Interaction

The program enters a loop that presents a menu to the user with the following options:

1. Enter as a librarian
2. Enter as a member
3. Exit

### Librarian Menu
1. Register a member.
2. Remove a member.
3. Add a book.
4. Remove a book.
5. View all books.
6. View all members with books and fines.
7. Exit as librarian.

### Member Menu
1. List available books.
2. List my books.
3. Issue a book.
4. Return a book.
5. Pay fines.
6. Exit as a member.


### Exit

If the user selects choice 3, the application exits.

## Exception Handling
The code includes exception handling to deal with potential input errors or other exceptions. It displays error messages when invalid input is provided and allows the user to retry.
if the phone number is of 10 digits and contains only numbers it is correct otherwise it shows error message
you have to give valid inputs ony like for a choice of member or a librarian should be a number (among the displayed) only otherwise it throws an error meassage.

## Closing Resources
After the user decides to exit the application, the `Scanner` object is closed to release system resources.


## Contributors

- Shashank Mishra


## Acknowledgments

This project was inspired by the need for an efficient library management system and was created to demonstrate good coding practices in Java.
