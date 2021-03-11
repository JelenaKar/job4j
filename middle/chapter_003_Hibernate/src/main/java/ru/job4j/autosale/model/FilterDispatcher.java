package ru.job4j.autosale.model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.autosale.entities.Ad;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Класс-диспетчер действий.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class FilterDispatcher {

    private static final FilterDispatcher INSTANCE = new FilterDispatcher();
    private final SessionFactory sf = new Configuration().configure("postgresql.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();
    private final Map<Filters, Function<Map<String, String[]>, List<Ad>>> dispatch = new HashMap<>();

    private FilterDispatcher() {
        this.dispatch.put(Filters.PER_DAY, this.perDay());
        this.dispatch.put(Filters.WITH_IMGS_ONLY, this.withImgs());
        this.dispatch.put(Filters.GIVEN_BRAND_ONLY, this.withBrand());
    }

    public static FilterDispatcher getInstance() {
        return INSTANCE;
    }

    public Function<Map<String, String[]>, List<Ad>> perDay() {
        return x -> dao.getForLastDay(sf);
    }

    public Function<Map<String, String[]>, List<Ad>> withImgs() {
        return x -> dao.getWithPhotos(sf);
    }

    public Function<Map<String, String[]>, List<Ad>> withBrand() {
        return x -> {
            int brandId = Integer.parseInt(x.get("brandId")[0]);
            return (brandId == 0) ? dao.findAll(Ad.class, sf) : dao.getOneBrandOnly(sf, brandId);
        };
    }

    /**
     * Загружает новый обработчик для действия
     * @param filter операция.
     * @param handle обработчик операции.
     */
    public void load(Filters filter, Function<Map<String, String[]>, List<Ad>> handle) {
        this.dispatch.put(filter, handle);
    }


    /**
     * Выполняет заданную операцию.
     * @param filter операция.
     * @return статус завершения операции.
     */
    public List<Ad> execute(Filters filter, Map<String, String[]> args) {
        return this.dispatch.get(filter).apply(args);
    }
}
