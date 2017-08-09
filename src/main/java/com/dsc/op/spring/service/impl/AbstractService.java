package com.dsc.op.spring.service.impl;

import com.dsc.op.spring.dao.DAO;
import com.dsc.op.spring.service.AbstractServiceInterface;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author COETO
 * @param <D>
 * @param <K>
 * @param <O>
 */
public abstract class AbstractService <D extends DAO, K extends Serializable,O extends Serializable> implements AbstractServiceInterface<D, K, O>{
    
    @Override
    public List<O> readAll() {
//        return dao.readAll();
        List<O> response=null;
        try {
            response = dao.buscarTodos();
        } catch (SQLException e) {
           e.printStackTrace();           
        }
        return response;
    }

//    @Override
//    public O read(K key){
////        return (O)dao.read(key);
//        O response=null;
//        try {
//            response = (O) dao.buscar(key);
//        } catch (SQLException e) {
//           e.printStackTrace();           
//        }
//        return response;
//    }
//
//    @Override
//    public boolean delete(K k){
////        return dao.delete(k);
//        boolean response=true;
//        try {
//            dao.eliminar(k);
//        } catch (SQLException e) {
//           e.printStackTrace();           
//        }
//        return response;
//    }
//
//    public boolean update(O o){
////        return dao.update(o);
//        boolean response=true;
//        try {
//            response = dao.actualizar(o);
//        } catch (SQLException e) {
//           e.printStackTrace();           
//        }
//        return response;
//    }
//
//    @Override
//    public boolean add(O o){
////        return dao.add(o);
//        boolean response=true;
//        try {
//            Integer id = dao.guardar(o);
//            response = id==1;
//        } catch (SQLException e) {
//           e.printStackTrace();           
//        }
//        return response;
//    }
//    
    @Autowired
    D dao;
}
