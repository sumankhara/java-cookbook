package javacookbook.chapter18.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateSimple {
    public static void main(String[] args) {
        System.out.println("HibernateSimple.main()");

        Configuration cf = new Configuration();
        cf.configure();
        SessionFactory sf = null;
        Session session = null;

        try {
            sf = cf.buildSessionFactory();
            session = sf.openSession();

            Transaction tx = session.beginTransaction();

            Address address = new Address("BA-37, Street No-126", "Kolkata", "India");
            System.out.println(address);

            session.save(address);
            tx.commit();

            int id = address.getId();
            System.out.println("Created address with id: " + id);

            tx = session.beginTransaction();

            Query query = session.createQuery("Select a from Address a order by a.city");

            List<Address> list = query.list();
            System.out.println("There are " + list.size() + " addresses");
            list.forEach(a -> System.out.println(a.getStreetAddress() + ", " + a.getCity() + ", " + a.getCountry()));
        } finally {
            if (session != null) {
                session.close();
            }
        }


    }
}
