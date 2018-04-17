package dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;

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
        System.out.println("WWWWWWEWE" + getSessionFactory());
        Query query = getSessionFactory().openSession().createQuery(hql);
        return query.uniqueResult().toString();
    }

}
