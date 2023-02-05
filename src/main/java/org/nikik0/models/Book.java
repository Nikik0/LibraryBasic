package org.nikik0.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int bookid;
    @NotEmpty(message = "Book name cant be empty")
    @Size(min = 2, max=100, message = "Book name length in not correct")
    private String name;
    @NotEmpty(message = "Author cant be empty")
    @Size(min = 2, max = 100, message = "Author name length isn't correct")
    private String author;
    private int customerid;
    //private Date whenTaken;
    public Book(int bookid, String name, String author) {
        this.bookid = bookid;
        this.name = name;
        this.author= author;
       // this.takenBy = -1;
    }

    public Book() {

    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTaken() {
        return customerid;
    }

    public void setTaken(int taken) {
        this.customerid = taken;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
