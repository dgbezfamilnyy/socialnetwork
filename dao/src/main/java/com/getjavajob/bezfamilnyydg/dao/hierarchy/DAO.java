package com.getjavajob.bezfamilnyydg.dao.hierarchy;

import java.util.List;

public interface DAO<E> {

    List<E> getAll();

    E create(E objectForInsert);

    void update(E objectForUpdate);

    void delete (E objectForDelete);

}
