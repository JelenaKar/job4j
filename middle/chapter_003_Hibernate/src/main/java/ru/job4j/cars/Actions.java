package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Function;

public class Actions {
    private static final Actions INSTANCE = new Actions();

    private Actions() {

    }

    public static Actions getInstance() {
        return INSTANCE;
    }

    public <E> E add(E model, SessionFactory sf) {
        return tx(sf, session -> {
            session.save(model);
            return model;
        });
    }

    public <E> E update(E model, SessionFactory sf) {
        return tx(sf, session -> {
            session.update(model);
            return model;
        });
    }

    public <E> E delete(E model, SessionFactory sf) {
        return tx(sf, session -> {
            session.delete(model);
            return model;
        });
    }

    public <E> E findById(Integer id, Class<E> cl, SessionFactory sf) {
        return tx(sf, session -> session.get(cl, id));
    }

    public <E, T> List<E> findByField(Class<E> cl, SessionFactory sf, String field, T val) {
        return tx(sf, session -> {
            Query<E> query = session.createQuery("from " + cl.getName() + " WHERE " + field + " = :" + field, cl);
            query.setParameter(field, val);
            return query.list();
        });
    }

    public <E> List<E> findAll(Class<E> cl, SessionFactory sf) {
        return tx(sf, session -> session.createQuery("from " + cl.getName(), cl).list());
    }

    public <E> List<E> findAll(Class<E> cl, SessionFactory sf, String order) {
        return tx(sf, session -> session.createQuery("from " + cl.getName() + " ORDER BY " + order, cl).list());
    }

    private <T> T tx(final SessionFactory sf, final Function<Session, T> command) {
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
