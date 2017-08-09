package com.dsc.op.spring.config;

import com.dsc.op.spring.dao.CustomerDao;
import com.dsc.op.spring.dao.impl.CustomerDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author COETO
 */
@Repository
public class DaoConfig {
    @Bean
    public CustomerDao getCustomerDao(){
        return new CustomerDaoImpl();
    }
}
