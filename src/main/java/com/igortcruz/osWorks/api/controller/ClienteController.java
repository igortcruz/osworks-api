package com.igortcruz.osWorks.api.controller;

import com.igortcruz.osWorks.domain.model.Cliente;
import com.igortcruz.osWorks.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private ClientRepository clienteRepository;

    @GetMapping("/clientes")
    public List<Cliente> Listar() {
        return clienteRepository.findAll();
//        return clienteRepository.findByNomeContaining("Igor");
//        return clienteRepository.findByNome("Iane Caroline Torres Cruz");
    }
}
