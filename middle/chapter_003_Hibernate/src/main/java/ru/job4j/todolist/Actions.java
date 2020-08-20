package ru.job4j.todolist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import java.util.function.Function;

public class Actions {

    public static Item add(Item item, SessionFactory sf) {
        return tx(sf, session -> {
            session.save(item);
            return item;
        });
    }

    public static Item update(Item item, SessionFactory sf) {
        return tx(sf, session -> {
            session.update(item);
            return item;
        });
    }

    public static Item findById(Integer id, SessionFactory sf) {
        return tx(sf, session -> session.get(Item.class, id));
    }

    public static List<Item> findAll(SessionFactory sf) {
        return tx(sf, session -> session.createQuery("from ru.job4j.todolist.Item ORDER BY created DESC").list());
    }

    private static <T> T tx(final SessionFactory sf, final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
