package org.example;



public class Member extends Library {
    protected String phoneNumber;
    protected String name;
    protected int age ;
    protected String memberId; // Change the type to String
    protected double penaltyAmount;
    protected Books book;
    protected Books borrowedBook1;
    protected Books borrowedBook2;

    protected long borrowedTimeMillis1;
    protected long borrowedTimeMillis2;
    public Member(String phoneNumber, String name ,int age) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.age=age;
        this.penaltyAmount = 0.0;
        this.memberId = idGen.generateUniqueMemberID();
        this.borrowedBook1 = null;
        this.borrowedBook2 = null;

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getMemberID() { // Change the return type to String
        return memberId;
    }

    public Books getBorrowedBook1() {
        return borrowedBook1;
    }

    public void setBorrowedBook1(Books book) {
        borrowedBook1 = book;
    }

    public Books getBorrowedBook2() {
        return borrowedBook2;
    }

    public void setBorrowedBook2(Books book) {
        borrowedBook2 = book;
    }
    public double getPenaltyAmount() {
        return penaltyAmount;
    }

    public void addPenalty(double amount) {
        penaltyAmount += amount;
    }

    public void setPenaltyAmount(double amount) {
        penaltyAmount = amount;
    }


    public void listAvailableBooks() {
        System.out.println("Available Books:");
        for (Books book : books) {
            if (book.getAvailableCopies() > 0) {
                System.out.println("Book ID: " + book.getBookID() +
                        ", Title: " + book.getTitle() +
                        ", Author: " + book.getAuthor() +
                        ", Available Copies: " + book.getAvailableCopies());
            }
        }
    }
    public void listMyBooks() {
        System.out.println("Borrowed Books for Member " + getName() + ":");

        if (getBorrowedBook1() != null) {
            System.out.println("  Book ID: " + getBorrowedBook1().getBookID() +
                    ",\nTitle: " + getBorrowedBook1().getTitle() +
                    "\nby " + getBorrowedBook1().getAuthor());
        }

        else if (getBorrowedBook2() != null) {
            System.out.println("  Book ID: " + getBorrowedBook2().getBookID() +
                    ", Title: " + getBorrowedBook2().getTitle() +
                    " by " + getBorrowedBook2().getAuthor());
        } else {
            System.out.println("  No books borrowed.");
        }
    }

    public void issueBook( Member member, String title, int id) {
        if (member == null ) {
            System.out.println("Invalid member or book.");
            return;
        }

        if (member.getPenaltyAmount() > 0) {
            System.out.println("You have remaining fines to pay. Please clear your dues before borrowing a book.");
            return;
        }

        if (member.getBorrowedBook1() != null && member.getBorrowedBook2() != null) {
            System.out.println("Member has already borrowed the maximum number of books.");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long borrowedTimeMillis = borrowedTimeMillis1 ;
        long elapsedSeconds = (currentTimeMillis - borrowedTimeMillis) / 1000;
        if (elapsedSeconds > 10 && borrowedTimeMillis!=0) {
            System.out.println("Some Books are pending for return their due time is over. return them first ");
            return;
        }
        boolean flag=false ;
        for(Books bk: books) {
            if(bk.getBookID()==id && bk.getTitle().equals(title)) {
                if (bk.getAvailableCopies() <= 0) {
                    System.out.println("Book is not available for borrowing.");
                    return;
                }
                bk.decreaseAvailableCopies();
                if (getBorrowedBook1() == null) {
                    setBorrowedBook1(bk);
                    borrowedTimeMillis1 = System.currentTimeMillis();
                } else if (getBorrowedBook2() == null) {
                    setBorrowedBook2(bk);
                    borrowedTimeMillis2 = System.currentTimeMillis();
                }
                flag=true;
                break;
            }
        }
        if(!flag){
            System.out.println("Book not Found");
            return;
        }

        System.out.println("Book '" + title + "' by " + member.getBorrowedBook1().getAuthor() +
                " issued to Member " + member.getName() + ".");
    }

    public void returnBook(Member member, int id) {
        book=new Books(0,null,null,0);
        for (Books bk:books){
            if(bk.getBookID()==id){
                book=bk;

            }
        }
        if (member == null ) {
            System.out.println("Invalid member or book.");
            return;
        }

        if (!book.equals(member.getBorrowedBook1()) && !book.equals(member.getBorrowedBook2())) {
            System.out.println("Member has not borrowed this book.");
            return;
        }

        long currentTimeMillis = System.currentTimeMillis();
        long borrowedTimeMillis = book.equals(borrowedBook1) ? borrowedTimeMillis1 : borrowedTimeMillis2;
        long elapsedSeconds = (currentTimeMillis - borrowedTimeMillis) / 1000;

        if (elapsedSeconds <= 10) {
            System.out.println("Book '" + book.getTitle() + "' by " + book.getAuthor() +
                    " returned by Member " + getName() + " within 1 seconds. No fine applied.");
        } else {
            long fineAmount =(elapsedSeconds-10)*3 ;
            addPenalty(fineAmount);
            System.out.println("Book '" + book.getTitle() + "' by " + book.getAuthor() +
                    " returned by Member " + getName() + " after " + (elapsedSeconds-10) +" day. A fine of Rs. "+fineAmount + " applied.");
        }


        if (book.equals(member.getBorrowedBook1())) {
            member.setBorrowedBook1(null);
            borrowedTimeMillis1=0;

        } else if (book.equals(member.getBorrowedBook2())) {
            member.setBorrowedBook2(null);
            borrowedTimeMillis2=0;

        }

        book.increaseAvailableCopies();

        System.out.println("Book '" + book.getTitle() + "' by " + book.getAuthor() +
                " returned by Member " + member.getName() + ".");
    }

    public void payFine(double amount) {
        if (amount < 0) {
            System.out.println("Invalid fine amount. Please provide a positive amount.");
            return;
        }

        if (getPenaltyAmount() == 0) {
            System.out.println(" No fines to pay.");
            return;
        }

        if (amount >= getPenaltyAmount()) {
            System.out.println("\nPaid Rs." + getPenaltyAmount() + " in fines.");
            setPenaltyAmount(0);
        } else {
            setPenaltyAmount(getPenaltyAmount() - amount); // Deduct the paid amount from fines
            System.out.println("Paid Rs." + amount + " in fines. Remaining balance: Rs" + getPenaltyAmount());
        }
    }



}


