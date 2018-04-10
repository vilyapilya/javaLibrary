package com.testpack;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Path("/test")
public class Book {

    private String title;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void setTitle(String title) {
        this.title = title;
    }

    public void add() {
        System.out.println("The book is added");
    }
}
