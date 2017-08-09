package com.dsc.op.spring.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author COETO
 * @param <Entity>
 * @param <Key>
 */
public interface DAO <Entity,Key>{
    Entity buscar(Key key) throws SQLException;
    Integer guardar(Entity entity) throws SQLException;
    boolean guardar(List<Entity> listaEntity) throws SQLException;
    List<Entity> buscarTodos() throws SQLException;
    boolean actualizar(Entity entity) throws SQLException;
    void eliminar(Key key) throws SQLException;
    void eliminarEntidad(Entity entity) throws SQLException;
}
