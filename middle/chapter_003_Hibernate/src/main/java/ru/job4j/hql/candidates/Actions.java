package ru.job4j.hql.candidates;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public <E, A, T> int update(Class<E> cl, SessionFactory sf, Map<String, A> values, Map<String, T> conditions) {
        return tx(sf, session -> {
            String newValues = " set ";
            String val = values.keySet().stream()
                    .map(a -> a + " = :" + a)
                    .collect(Collectors.joining(", "));
            newValues += val;

            String conditionString = " where ";
            String where = conditions.keySet().stream()
                    .map(t -> t + " = :" + t)
                    .collect(Collectors.joining(" and "));
            conditionString += where;
            Query query = session.createQuery("update " + cl.getName() + newValues + conditionString);
            values.entrySet().stream().forEach(e -> query.setParameter(e.getKey(), e.getValue()));
            conditions.entrySet().stream().forEach(e -> query.setParameter(e.getKey(), e.getValue()));
            return query.executeUpdate();
        });
    }

    public <E, T> int delete(Class<E> cl, SessionFactory sf, Map<String, T> conditions) {
        return tx(sf, session -> {
            String conditionString = " where ";
            String where = conditions.keySet().stream()
                    .map(t -> t + "= :" + t)
                    .collect(Collectors.joining(" and "));
            conditionString += where;
            Query query = session.createQuery("delete from " + cl.getName() + conditionString);
            conditions.entrySet().stream().forEach(e -> query.setParameter(e.getKey(), e.getValue()));
            return query.executeUpdate();
        });
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
            String conditionString = " where ";
            String where = conditions.keySet().stream()
                    .map(t -> t + "= :" + t)
                    .collect(Collectors.joining(" and "));
            conditionString += where;
            Query<E> query = session.createQuery("from " + cl.getName() + conditionString, cl);
            conditions.entrySet().stream().forEach(e -> query.setParameter(e.getKey(), e.getValue()));
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
