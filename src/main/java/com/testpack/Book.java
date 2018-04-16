package com.testpack;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class Book {

    private String title;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTitle() {
        return "ttttt";
    }

    public void add() {
        System.out.println("The book is added");
    }
}
