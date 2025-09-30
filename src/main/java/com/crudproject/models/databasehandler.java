package com.crudproject.models;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class databasehandler {
    private static SessionFactory sessionFactory = BuildSessionFactory();

    private static SessionFactory BuildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            System.err.println("creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }



}
