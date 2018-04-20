package dao;

import com.testpack.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;



import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class HibernateDao {

    @Autowired
    private SessionFactory sessionFactory;
    private static StandardServiceRegistry standardServiceRegistry;

    public SessionFactory getSessionFactory() {
        if(this.sessionFactory == null) {
            standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
            MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
            Metadata metadata = metadataSources.getMetadataBuilder().build();
            this.sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        return this.sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public String getBook() {
        String hql = "select count(*) from Book";
        Query query = getSessionFactory().openSession().createQuery(hql);
        return query.uniqueResult().toString();
    }

    public List<String> getTitles() {
        String hql = "select title from Book";
        Query query = getSessionFactory().openSession().createQuery(hql);
        return query.getResultList();
    }

    public void addBookEntry(String title, int author_id) {
        Session session  = getSessionFactory().openSession();
        session.beginTransaction();
        Book bookEnt = new Book();
        bookEnt.setShowBooks(title);
        bookEnt.setAuthor_id(author_id);

        session.save(bookEnt);
        session.getTransaction().commit();
    }

    public void deleteBookEntry(int bookId) {
        Session session  = getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("delete from Book where id = :bookId");
        q.setParameter("bookId", bookId);
        q.executeUpdate();
        session.getTransaction().commit();
    }

}
