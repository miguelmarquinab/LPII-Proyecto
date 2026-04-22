package com.lp2.sisinventario.sistemainventario.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.lp2.sisinventario.sistemainventario.model.Cliente;
import com.lp2.sisinventario.sistemainventario.repository.ClienteRepository;

@Service
@RequiredArgsConstructor
public class ClienteService {
        
    private final ClienteRepository clienteRepository;
    
    public List<Cliente> getClientes(){
        return clienteRepository.findAll();
    }
    
    public Cliente getClienteById(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }
    
    public void guardar(Cliente cliente) {
        clienteRepository.save(cliente);
    }
    
    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
}