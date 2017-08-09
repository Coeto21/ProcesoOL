package com.dsc.op.spring.service.impl;

import com.dsc.op.spring.dao.CustomerDao;
import com.dsc.op.spring.entities.Customer;
import com.dsc.op.spring.service.CustomerService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author COETO
 */
@Service
public class CustomerServiceImpl extends AbstractService<CustomerDao, Integer, Customer> implements CustomerService{ //  implements CustomerService

    @Override
    public List<Customer> readAll() {
        List<Customer> response = new ArrayList<>();
        try {
            response = customerDao.buscarTodos();            
        } catch (SQLException ex) {
            System.out.println("Error em busqueda"+ex.getCause());
        }
        return response;
    }
    
    @Override
    public Customer read(Integer key) {
        Customer response=null;
        try {
            response = customerDao.buscar(key);
        } catch (SQLException ex) {
            System.out.println("Error en lectura "+ex.getCause());
        }
        return response;
    }

    @Override
    public boolean delete(Integer k) {
        boolean response=false;
        try {
            customerDao.eliminar(k);
            response=true;
        } catch (SQLException ex) {
            logger.error("Error al eliminar Entidad: "+ex.getMessage());
        }
        
        return response;
    }

    @Override
    public boolean update(Customer o) {
        boolean response=false;
        try {
            response = customerDao.actualizar(o);
        } catch (SQLException ex) {
            logger.error("Error al guardar Entidad: "+ex.getMessage());
        }
        
        return response;
    }

    @Override
    public boolean add(Customer o) {
        Integer response=0;
        try {
            response = customerDao.guardar(o);
        } catch (SQLException ex) {
            logger.error("Error al guardar Entidad: "+ex.getMessage());
        }
        
        return response==1;
    }
    
    @Autowired
    CustomerDao customerDao;
    private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class);
}
