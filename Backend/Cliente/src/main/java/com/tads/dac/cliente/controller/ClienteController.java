
package com.tads.dac.cliente.controller;

import com.tads.dac.cliente.DTO.ClienteEndDTO;
import com.tads.dac.cliente.exception.ClienteNotFoundException;
import com.tads.dac.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cli")
public class ClienteController {
    
    @Autowired
    private ClienteService serv;
    
    //Usar com apiCompose - caso precise
    //R4 - use pra pegar os dados do cliente
    @GetMapping("/cli/{id}")
    public ResponseEntity<?> getCliente(@PathVariable(value = "id") Long id){
        try{
            ClienteEndDTO dto = serv.getClienteById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
         } catch (ClienteNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    //R2 pra fazer apiCompose
    @GetMapping("/cli/email/{email}")
    public ResponseEntity<?> getClienteByEmail(@PathVariable(value = "email") String email){
        try{
            ClienteEndDTO dto = serv.getClienteByEmail(email);
        return new ResponseEntity<>(dto, HttpStatus.OK);
         } catch (ClienteNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
}
