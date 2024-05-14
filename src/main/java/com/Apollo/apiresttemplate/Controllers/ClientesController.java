package com.Apollo.apiresttemplate.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class ClientesController {

    private final String API_URL = "http://localhost:3000/Clientes";

    private final RestTemplate restTemplate;

    @Autowired
    public ClientesController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/listar-clientes")
    public ResponseEntity<String> listarClientes() {
        ResponseEntity<String> response = restTemplate.getForEntity(API_URL, String.class);

        return response;
    }
}
