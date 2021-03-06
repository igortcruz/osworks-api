package com.igortcruz.osWorks.api.controller;

import com.igortcruz.osWorks.domain.model.Cliente;
import com.igortcruz.osWorks.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClientRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
//        return clienteRepository.findByNomeContaining("Igor");
//        return clienteRepository.findByNome("Iane Caroline Torres Cruz");
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if (cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId,@RequestBody Cliente cliente){

        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clienteId);
        cliente = clienteRepository.save(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> remover(@PathVariable Long clienteId){
        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }

        clienteRepository.deleteById(clienteId);

        return ResponseEntity.noContent().build();
    }
}
