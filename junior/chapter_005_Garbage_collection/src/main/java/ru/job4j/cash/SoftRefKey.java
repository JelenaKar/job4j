package ru.job4j.cash;

import java.lang.ref.SoftReference;
import java.util.Objects;

/**
 * Класс-наследник SoftReference.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class SoftRefKey<T> extends SoftReference<T> {
    public SoftRefKey(T referent) {
        super(referent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.get());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SoftRefKey<?>))  {
            return false;
        }
        SoftRefKey softRef = (SoftRefKey) obj;
        return Objects.equals(this.get(), softRef.get());
    }
}
