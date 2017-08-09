package com.dsc.op.spring.config;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
/**
 *
 * @author COETO
 */
@Repository
public class DataSource {
    
    @Bean
    public static BasicDataSource getDataSource(){
        if(!(dataSource!=null)){
            BasicDataSource bds = new BasicDataSource();
            bds.setDriverClassName("oracle.jdbc.OracleDriver");
            bds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
            bds.setUsername("dbnewuser");
            bds.setPassword("bbva01");
            bds.setMaxActive(15);
            bds.setMinIdle(2);
            bds.setMaxIdle(5);
            bds.setMaxOpenPreparedStatements(100);
            bds.setTimeBetweenEvictionRunsMillis(10000);
            
            dataSource=bds;
        }        
        return dataSource;
    }
    
    private static BasicDataSource dataSource;
}
