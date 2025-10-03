package com.crudproject.models;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.crudproject.models.User;
import com.crudproject.models.databasehandler;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class pwhasher {
    public void hashPassword(String name, String RawPassword, String email) {
        String HasedPassword = BCrypt.withDefaults().hashToString(12, RawPassword.toCharArray());
        Session session = databasehandler.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        User user = new User(name, HasedPassword, email);
        session.persist(user);

        tx.commit();
        session.close();

    }

    public boolean checkPassword(String name, String RawPassword) {
        Session session = databasehandler.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("from User where name = :name");
            query.setParameter("name", name);
            User user = (User) query.uniqueResult();
            tx.commit();

            if (user == null) {
                return false;
            }

            BCrypt.Result result = BCrypt.verifyer()
                    .verify(RawPassword.toCharArray(), user.getPassword());
            return result.verified;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
        }


    }
}
