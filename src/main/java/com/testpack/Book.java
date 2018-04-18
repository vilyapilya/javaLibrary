package com.testpack;

import dao.HibernateDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/test")
@Entity
@Table(name="books")
public class Book {

    private int id;
    private String title;
    private int author_id;

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setShowBooks(String title) {
        this.title = title;
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String showBooks() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Beans.xml");
        HibernateDao dao = ctx.getBean("hibernateDao", HibernateDao.class);
        List<String> result = dao.getTitles();
        return result.toString();
    }

    @GET
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    public String createBook() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Beans.xml");
        HibernateDao dao = ctx.getBean("hibernateDao", HibernateDao.class);
        dao.addBookEntry("New Book", 2);
        return "Adding a bew book";

    }

    public void add() {
        System.out.println("The book is added");
    }
}
