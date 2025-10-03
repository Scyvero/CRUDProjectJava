package com.crudproject.models;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserRepository {
    public List<User> findAllUsers() {
        Session session = databasehandler.getSessionFactory().openSession();
        Transaction tx = null;
        List<User> users = null;
        try{
            tx = session.beginTransaction();
            users = session.createQuery("from User", User.class).list();
            tx.commit();

        }
        catch(Exception e){
            e.printStackTrace();
            assert tx != null;
            tx.rollback();
        }
        finally {
            session.close();
        }
        return users;
    }
    public void DeleteUser(User user) {
        Session session = databasehandler.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.remove(user);
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
    public void SaveUser(User user) {
        Session session = databasehandler.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.persist(user);
            tx.commit();

        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void UpdateUser(User user) {
        Session session = databasehandler.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

}
