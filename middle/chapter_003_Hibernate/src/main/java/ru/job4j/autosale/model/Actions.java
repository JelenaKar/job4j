package ru.job4j.autosale.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;
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
            session.saveOrUpdate(model);
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

    public <E, T> List<E> findByConditions(Class<E> cl, SessionFactory sf, Map<String, T> conditions) {
        return tx(sf, session -> {
            String conditionString = " WHERE ";
            for (Map.Entry<String, T> condition : conditions.entrySet()) {
                conditionString += condition.getKey() + " = :" + condition.getKey();
            }
            Query<E> query = session.createQuery("from " + cl.getName() + conditionString, cl);
            for (Map.Entry<String, T> cond : conditions.entrySet()) {
                query.setParameter(cond.getKey(), cond.getValue());
            }
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
