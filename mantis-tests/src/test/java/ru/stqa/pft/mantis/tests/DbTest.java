package ru.stqa.pft.mantis.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UserData;

import java.util.List;

public class DbTest {
    private SessionFactory sessionFactory;

    @BeforeTest
    protected void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Test
    public void testDb() throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery( "from UserData where id != 1" ).list();
        for ( UserData user : result) {
            System.out.println(user);
        }
        session.getTransaction().commit();
        session.close();
    }
}
