package org.example;

public class Librarian extends Library {
    private static int lastBookID = count;
    public void registerMember(String name, String phoneNumber, int age) {
        for (Member existingMember : members) {
            if (existingMember.getName().equals(name) && existingMember.getPhoneNumber().equals(phoneNumber)) {
                System.out.println("Member with the same name and phone number already exists. Registration failed.");
                return;
            }
            if (existingMember.getPhoneNumber().equals(phoneNumber)) {
                System.out.println("Member with the same phone number already exists. Registration failed.");
                return;
            }
        }
        Member member = new Member(phoneNumber,name,age);
        members.add(member);
        System.out.println("Member registered successfully!");
    }


    public void removeMember(String name, String phoneNumber) {
        Member memberToRemove = null;

        for (Member member : members) {
            if (member.getName().equals(name) && member.getPhoneNumber().equals(phoneNumber)) {
                memberToRemove = member;
                break;
            }
        }

        if (memberToRemove != null) {
            members.remove(memberToRemove);
            System.out.println("Member removed successfully!");
        } else {
            System.out.println("Member not found. Unable to remove.");
        }
    }

    public void addBook(String title, String author, int totalCopies) {

        for (Books existingBook : books) {
            if (existingBook.getTitle().equals(title) && existingBook.getAuthor().equals(author)) {
                existingBook.increaseAvailableCopies();
                System.out.println("Book already exists. Available copies increased.");
                return;
            }
        }
        for(int i = 0; i < totalCopies; i++) {
           // int newBookID = ++count ;
            int newBookID = ++lastBookID;
            Books newBook = new Books(newBookID, title, author,++count);
            books.add(newBook);

        }
        System.out.println("Book added successfully.");
    }

    public void removeBook(String title,int id) {

        for (Books existingBook : books) {
            if (existingBook.getTitle().equals(title) && existingBook.getBookID()==(id)) {
                books.remove(existingBook);
                System.out.println("Book removed from the collection.");
                return;
            }
        }
        System.out.println("Book does not exists.");
    }
    public void viewAllBooks() {
        System.out.println("All Books:");
        for (Books book : books) {
            if (book.getAvailableCopies() > 0) {
                System.out.println("Book ID: " + book.getBookID() +
                        ",\n Title: " + book.getTitle() +
                        ",\n Author: " + book.getAuthor());
            }
        }
    }
    public void viewAllMembersWithBooksAndFines() {
        System.out.println("All Members with Books and Fines:");
        for (Member member : members) {
            System.out.println("Member ID: " + member.getMemberID() +
                    ",\nName: " + member.getName() +
                    ",\nPhone Number: " + member.getPhoneNumber());


            if (member.getBorrowedBook1() != null) {
                System.out.println("  Borrowed Book 1: " + member.getBorrowedBook1().getTitle() +
                        " by " + member.getBorrowedBook1().getAuthor());
            }

            if (member.getBorrowedBook2() != null) {
                System.out.println("  Borrowed Book 2: " + member.getBorrowedBook2().getTitle() +
                        " by " + member.getBorrowedBook2().getAuthor());
            }

            System.out.println("  Fines to be Paid: Rs." + member.getPenaltyAmount());
        }
    }


}

