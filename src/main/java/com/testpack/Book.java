package com.testpack;

public class Book {

    private String authorFirstName;

    public void setAuthorFirstName(String name) {
        this.authorFirstName = name;
    }

    public void add() {
        System.out.println("The book is added");
    }
}
