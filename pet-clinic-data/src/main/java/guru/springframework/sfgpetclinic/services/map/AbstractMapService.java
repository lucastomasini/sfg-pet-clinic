package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {
    protected Map<Long, T> map = new HashMap<>();

    T findById(ID id) {
        return map.get(id);
    }

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T save(T object) {
        if (object == null) {
            throw new RuntimeException("Object cannot be null");
        }

        Long nextId = object.getId();

        if (nextId == null) {
            nextId = map.isEmpty() ? 1L : Collections.max(map.keySet()) + 1;
            object.setId(nextId);
        }

        map.put(nextId, object);

        return object;
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void delete(T object) {
        map.entrySet().removeIf(e -> e.getValue().equals(object));
    }
}
