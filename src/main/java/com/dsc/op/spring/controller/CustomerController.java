package com.dsc.op.spring.controller;

import com.dsc.op.spring.entities.Customer;
import com.dsc.op.spring.service.CustomerService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author COETO
 */
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    
    @ResponseBody   
    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveCustomer(@RequestBody Customer customer){
        customerService.add(customer);
        return true;
    }
    
    @ResponseBody
    @RequestMapping(value="{idCustomer}",method=RequestMethod.GET)
    public Customer getCustomer(@PathVariable(name = "idCustomer", required = true) Integer idCustomer){        
        Customer customer = customerService.read(idCustomer);
        return customer;
    }
    
    @ResponseBody
    @RequestMapping(method=RequestMethod.GET)
    public List<Customer> getAllCustomers(){
          List<Customer> listCustomer = customerService.readAll();
        return listCustomer;
    }
    
    @ResponseBody
    @RequestMapping(method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateBank(@RequestBody Customer customer){
        logger.trace("doing put");
        customerService.update(customer);
        return true;
    }
    
    @ResponseBody
    @RequestMapping(value="{idCustomer}", method=RequestMethod.DELETE)
    public boolean deleteCustomer(@PathVariable(name = "idCustomer", required = true) Integer idCustomer){
        logger.trace("doing delete");
        boolean returnValue=customerService.delete(idCustomer);
        return returnValue;
    }
    
    @Autowired
    CustomerService customerService;
    private static final Logger logger = Logger.getLogger(CustomerController.class);
}
