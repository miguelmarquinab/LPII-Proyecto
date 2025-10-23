package com.lp2.sisinventario.sistemainventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lp2.sisinventario.sistemainventario.model.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ClienteService {
        
    @PersistenceContext
    EntityManager entityManager;
    
    public List<Cliente> getClientes(){
    	String query = "FROM Cliente";
    	return entityManager.createQuery(query).getResultList();
    }
}
