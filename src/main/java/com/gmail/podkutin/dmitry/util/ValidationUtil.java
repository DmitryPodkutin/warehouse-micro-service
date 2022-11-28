package com.gmail.podkutin.dmitry.util;

import com.gmail.podkutin.dmitry.exeption.NotFoundException;

import java.util.Optional;

public class ValidationUtil {

    public static <T> T checkNotFoundWithId(Optional<T> object, int id) {
        checkNotFoundWithId(object.isPresent(), id);
        return object.get();
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id =" + id);
    }


    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException(" Not found_entity with " + msg);
        }
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalArgumentException(bean + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
//      conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.hasId() != id) {
            throw new IllegalArgumentException(bean + " must be with id=" + id);
        }
    }
}
