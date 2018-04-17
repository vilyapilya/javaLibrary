package com.testpack;

import dao.HibernateDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
@Entity
public class Book {

    @Id
    private int id;
    private String title;
    private int author_id;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTitle() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Beans.xml");

        HibernateDao dao = ctx.getBean("hibernateDao", HibernateDao.class);
        String result = dao.getBook();
        return result;
    }

    public void add() {
        System.out.println("The book is added");
    }
}
