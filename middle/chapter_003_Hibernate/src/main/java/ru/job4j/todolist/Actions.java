package ru.job4j.todolist;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class Actions {

    public static Item add(Item item, SessionFactory sf) {
        try {
            Session session = sf.openSession();
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return item;
    }

    public static Item update(Item item, SessionFactory sf) {
        try {
            Session session = sf.openSession();
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return item;
    }

    public static Item findById(Integer id, SessionFactory sf) {
        Item result = null;
        try {
            Session session = sf.openSession();
            session.beginTransaction();
            result = session.get(Item.class, id);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return result;
    }

    public static List<Item> findAll(SessionFactory sf) {
        List result = new ArrayList<>();
        try {
            Session session = sf.openSession();
            session.beginTransaction();
            result = session.createQuery("from ru.job4j.todolist.Item ORDER BY created DESC").list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return result;
    }
}
