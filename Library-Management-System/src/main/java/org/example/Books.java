package org.example;

public class Books extends Library{

    protected  int bookID;
    protected  String title;
    protected  String author;
    protected  int totalCopies;
    protected int availableCopies;

    public Books(int bookID,String title, String author,int count) {
        this.bookID = count;
        this.title = title;
        this.author = author;
        this.totalCopies = 1;
        availableCopies = 1;
    }
    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void decreaseAvailableCopies() {
        if (availableCopies > 0) {
            availableCopies--;
        }
    }


    public void increaseAvailableCopies() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }
}

