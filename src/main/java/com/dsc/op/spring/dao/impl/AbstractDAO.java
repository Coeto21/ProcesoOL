package com.dsc.op.spring.dao.impl;

import com.dsc.op.spring.dao.DAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author COETO
 * @param <K>
 * @param <O>
 */

public abstract class AbstractDAO <O extends Serializable,K> implements DAO<O,K>{
    @Autowired
    private DataSource dataSource;
    protected String tableName="CUSTOMER";
    protected String idField="CD_CUSTOMER";
    protected String sequenceName="CUSTOMER_SEQ";

    protected List<O> getResultSet(ResultSet rSet) throws SQLException {
        throw new UnsupportedOperationException("Not implemented yet");        
    }

    protected PreparedStatement insertPreparedStatement(Connection con, Integer sequenceId, O entity) throws SQLException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected PreparedStatement insertPreparedStatement(Connection con, List<O> entity) throws SQLException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected PreparedStatement updatePreparedStatement(Connection con, O entity) throws SQLException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected Integer getNextId() throws SQLException {
        Integer id = -1;
        StringBuilder selectNexValBuilder = new StringBuilder(25);
        selectNexValBuilder.append("SELECT ").append(sequenceName).append(".nextval next_val FROM DUAL");
        String selectNextVal = selectNexValBuilder.toString();
        try (Connection con = getDataSource().getConnection();
                PreparedStatement pstmt = con.prepareStatement(selectNextVal);
                ResultSet rSet = pstmt.executeQuery()) {
            while (rSet.next()) {
                id = rSet.getInt("next_val");
            }
        } catch (SQLException sqle) {
            id = -1;
            throw new SQLException("Error al generar la secuencia " + sequenceName, sqle);
        }
        return id;
    }

    @Override
    public O buscar(K key) throws SQLException {
        StringBuilder selectQueryBuilder = new StringBuilder(10);
        selectQueryBuilder.append("SELECT * FROM ").append(tableName)
                .append(" WHERE ").append(idField).append("=?");
        String select = selectQueryBuilder.toString();
        O registro = null;
        try (Connection con = getDataSource().getConnection();
                PreparedStatement pstmt = con.prepareStatement(select)) {
            pstmt.setObject(1, key);
            try (ResultSet rSet = pstmt.executeQuery()) {
                List<O> listaRegistros = getResultSet(rSet);
                if (listaRegistros.size() > 0) {
                    registro = listaRegistros.get(0);
                }
            } catch (SQLException sqle) {
                throw sqle;
            }
        } catch (SQLException sqle) {
            System.out.println("errror sqle " + sqle);
            sqle.printStackTrace();
        }
        return registro;
    }

    @Override
    public Integer guardar(O entity) throws SQLException {
        Integer nextId=null;
            nextId = getNextId();
        try (Connection con = getDataSource().getConnection();
                PreparedStatement pstmt = insertPreparedStatement(con, nextId, entity)) {
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            throw sqle;
        }

        return nextId;
    }

    @Override
    public boolean guardar(List<O> listaEntity) throws SQLException {
        boolean guardado = false;
        try (Connection con = getDataSource().getConnection();
                PreparedStatement pstmt = insertPreparedStatement(con, listaEntity)) {
            pstmt.executeBatch();
            guardado = true;
        } catch (SQLException sqle) {
            throw sqle;
        }
        return guardado;
    }

    @Override
    public List<O> buscarTodos() {
        StringBuilder selectQueryBuilder = new StringBuilder(10);
        selectQueryBuilder.append("SELECT * FROM ").append(tableName);
        String select = selectQueryBuilder.toString();
        List<O> listaRegistros = null;
        try (Connection con = getDataSource().getConnection();
                PreparedStatement pstmt = con.prepareStatement(select)) {
            try (ResultSet rSet = pstmt.executeQuery()) {
                listaRegistros = getResultSet(rSet);
            } catch (SQLException sqle) {
                throw sqle;
            }
        } catch (SQLException sqle) {
            System.out.println("errror sqle " + sqle);
        }
        return listaRegistros;
    }

    @Override
    public boolean actualizar(O entity) throws SQLException {
        boolean actualizado = false;
        try (Connection con = getDataSource().getConnection();
                PreparedStatement pstmt = updatePreparedStatement(con, entity)) {
            pstmt.execute();
            actualizado = true;
        } catch (SQLException sqle) {
            throw sqle;
        }

        return actualizado;
    }

    @Override
    public void eliminar(K key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminarEntidad(O entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected DataSource getDataSource() {
        return dataSource;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public String getIdField() {
        return idField;
    }
}
