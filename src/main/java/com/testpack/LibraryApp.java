package com.testpack;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.naming.InitialContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;
import javax.servlet.ServletContext;
import java.util.Set;
import java.util.HashSet;

@ApplicationPath("/")
public class LibraryApp {
    protected ApplicationContext springContext;

    @Context
    protected ServletContext servletContext;

    public Set<Object> getSingletons() {
        try
        {
            InitialContext ctx = new InitialContext();
            String xmlFile = (String)servletContext.getInitParameter
                    ("Beans");
            springContext = new ClassPathXmlApplicationContext(xmlFile);
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
        HashSet<Object> set = new HashSet();
        set.add(springContext.getBean("book"));
        return set;
    }
}
