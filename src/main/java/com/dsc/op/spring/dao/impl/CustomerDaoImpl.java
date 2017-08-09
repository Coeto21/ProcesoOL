package com.dsc.op.spring.dao.impl;

import com.dsc.op.spring.dao.CustomerDao;
import com.dsc.op.spring.entities.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author COETO
 */
public class CustomerDaoImpl extends AbstractDAO<Customer,Integer> implements CustomerDao{
    
    @Override
    protected List<Customer> getResultSet(ResultSet rSet) throws SQLException {
         List<Customer> listaRegistros = new ArrayList<>();
        while (rSet.next()) {
            Integer id = rSet.getInt("CD_CUSTOMER");
            String name = rSet.getString("NB_CUSTOMER");
            String address = rSet.getString("TX_ADDRESS");
            String phone = rSet.getString("TX_PHONE");            

            Customer response = new Customer();
            response.setId(id);
            response.setName(name);
            response.setAddress(address);
            response.setNumber(phone);
            listaRegistros.add(response);
        }
        
        return listaRegistros;
    }
    
    @Override
    protected PreparedStatement insertPreparedStatement(Connection con, Integer sequenceId, Customer entity) throws SQLException {
        StringBuilder st = new StringBuilder();
        
        st.append(" INSERT INTO ").append(tableName)
                .append(" (CD_CUSTOMER, NB_CUSTOMER, TX_ADDRESS, TX_PHONE) ")
                .append(" VALUES (?,?,?,?) ");
        
        String query = st.toString();
        PreparedStatement pstmt = con.prepareStatement(query);
        System.out.println("ID: "+sequenceId);
        pstmt.setInt(1,sequenceId);
        pstmt.setString(2, entity.getName());
        pstmt.setString(3, entity.getAddress());
        pstmt.setString(4, entity.getNumber());
        
        return pstmt;
        
    }
    
    @Override
    protected PreparedStatement insertPreparedStatement(Connection con, List<Customer> entities) throws SQLException {
        StringBuilder st = new StringBuilder();
        
        st.append(" INSERT INTO ").append(tableName)
                .append(" (CD_CUSTOMER, NB_CUSTOMER, TX_ADDRESS, TX_PHONE) ")
                .append(" VALUES (?,?,?,?) ");
        
        String query = st.toString();
        PreparedStatement pstmt = con.prepareStatement(query);
        
        for(Customer entity: entities){
            pstmt.setInt(1,entity.getId());
            pstmt.setString(2, entity.getName());
            pstmt.setString(3, entity.getAddress());
            pstmt.setString(4, entity.getNumber());
            pstmt.addBatch();
        }
        
        return pstmt;
    }
    
    @Override
    protected PreparedStatement updatePreparedStatement(Connection con, Customer entity) throws SQLException {
        StringBuilder st = new StringBuilder();
        
        st.append(" UPDATE ").append(tableName)
                .append(" SET NB_CUSTOMER=?, TX_ADDRESS=?, TX_PHONE=? ")
                .append(" WHERE CD_CUSTOMER=? ");
        
        String query = st.toString();
        PreparedStatement pstmt = con.prepareStatement(query);
                
        pstmt.setString(1, entity.getName());
        pstmt.setString(2, entity.getAddress());
        pstmt.setString(3, entity.getNumber());
        pstmt.setInt(4,entity.getId());
        
        return pstmt;
    }
    
    @Override
    public void eliminar(Integer key) {
         StringBuilder st = new StringBuilder();
        
        st.append(" DELETE FROM ").append(tableName)
                .append(" WHERE CD_CUSTOMER=? ");       
        
        String query = st.toString();
        try(Connection con = getDataSource().getConnection();
               PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setInt(1, key);
        try(ResultSet rSet = pstmt.executeQuery()){
            
        }
       }catch(SQLException e){
           e.printStackTrace();
       }
        
    }
}
