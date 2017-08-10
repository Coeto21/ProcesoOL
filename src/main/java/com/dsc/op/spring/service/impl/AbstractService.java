package com.dsc.op.spring.service.impl;

import com.dsc.op.spring.dao.DAO;
import com.dsc.op.spring.service.AbstractServiceInterface;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author COETO
 * @param <D>
 * @param <K>
 * @param <O>
 */
public abstract class AbstractService <D extends DAO, K extends Serializable,O extends Serializable> implements AbstractServiceInterface<D, K, O>{
    @Autowired
    D dao;
}
