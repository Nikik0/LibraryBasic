package org.nikik0.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;

public class Customer {
    private int customerid;
    @NotEmpty(message = "Name cant be empty")
    @Size(min = 2, max = 30, message = "Name length is incorrect")
    private String name;
    @Max(value = 200, message = "Age cant be this big")
    @Min(value = 0, message = "Age cant be less than 0")
    private int age;
    private ArrayList<Book> booksTaken;

    public Customer(int customerid, String name, int age) {
        this.customerid = customerid;
        this.name = name;
        this.age = age;
        this.booksTaken = new ArrayList<>();
    }

    public Customer() {

    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Book> getBooksTaken() {
        return booksTaken;
    }

    public void setBooksTaken(ArrayList<Book> booksTaken) {
        this.booksTaken = booksTaken;
    }
}
