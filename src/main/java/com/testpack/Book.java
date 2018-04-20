package com.testpack;

import dao.HibernateDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//Figure out ctx part
//Find out about schema creation
@Path("/test")
@Entity
@Table(name="books")
@Transactional
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
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String showSingleBook(@PathParam("id") int id) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Beans.xml");
        HibernateDao dao = ctx.getBean("hibernateDao", HibernateDao.class);
        return dao.getBook(id);
    }

    //Figure out JSON
    @POST
    @Path("/add")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
    public String createBook(@FormParam("author_id") int author_id,
                             @FormParam("title") String title) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Beans.xml");
        HibernateDao dao = ctx.getBean("hibernateDao", HibernateDao.class);
        dao.addBookEntry(title, author_id);
        return "Adding a bew book";
    }

    @POST
    @Path("/remove/{id}")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String deleteBook(@PathParam("id") int bookId) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Beans.xml");
        HibernateDao dao = ctx.getBean("hibernateDao", HibernateDao.class);
        dao.deleteBookEntry(bookId);
        return ("the book is deleted");
    }

    @POST
    @Path("/edit/{id}")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String updateBook(@PathParam("id") int bookId,
                             @FormParam("author_id") int author_id,
                             @FormParam("title") String title) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Beans.xml");
        HibernateDao dao = ctx.getBean("hibernateDao", HibernateDao.class);
        dao.updateBookEntry(bookId, title, author_id);
        return ("the book is edited");
    }
}
