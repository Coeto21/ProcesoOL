package com.dsc.op.spring.service;

import com.dsc.op.spring.dao.DAO;
import java.util.List;

/**
 *
 * @author COETO
 * @param <D>
 * @param <K>
 * @param <O>
 */
public interface AbstractServiceInterface <D extends DAO, K, O>{
    List<O> readAll();
    O read(K key);
    boolean delete(K k);
    boolean update(O o);
    boolean add(O o);
}
