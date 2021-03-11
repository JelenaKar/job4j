package ru.job4j.autosale.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.autosale.entities.Ad;

import java.time.*;
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
            String conditionString = " where ";
            String where = conditions.keySet().stream()
                    .map(t -> t + "= :" + t)
                    .collect(Collectors.joining(" and "));
            conditionString += where;
            Query<E> query = session.createQuery("from " + cl.getName() + conditionString, cl);
            conditions.entrySet().forEach(e -> query.setParameter(e.getKey(), e.getValue()));
            return query.list();
        });
    }

    public <E> List<E> findAll(Class<E> cl, SessionFactory sf) {
        return tx(sf, session -> session.createQuery("from " + cl.getName(), cl).list());
    }

    public <E> List<E> findAll(Class<E> cl, SessionFactory sf, String order) {
        return tx(sf, session -> session.createQuery("from " + cl.getName() + " ORDER BY " + order, cl).list());
    }

    public List<Ad> getForLastDay(SessionFactory sf) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MIDNIGHT);
        long perDay = ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).toInstant().toEpochMilli();
        return tx(sf, session -> {
            Query<Ad> query = session.createNamedQuery("Ad_GetForLastDay");
            query.setParameter("perDay", perDay);
            return query.list();
        });
    }

    public List<Ad> getWithPhotos(SessionFactory sf) {
        return tx(sf, session -> {
            Query<Ad> query = session.createNamedQuery("Ad_GetWithPhotos");
            return query.list();
        });
    }

    public List<Ad> getOneBrandOnly(SessionFactory sf, int brandId) {
        return tx(sf, session -> {
            Query<Ad> query = session.createNamedQuery("Ad_GetOneBrandOnly");
            query.setParameter("brandId", brandId);
            return query.list();
        });
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
