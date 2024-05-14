package com.Apollo.apiresttemplate.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.Apollo.apiresttemplate.Services.ClienteServices;

@RestController
@RequestMapping("/")
public class ClientesController {

    private final String API_URL = "http://localhost:3000/Clientes";

    private final RestTemplate restTemplate;
    private final ClienteServices clienteService;

    @Autowired
    public ClientesController(RestTemplate restTemplate, ClienteServices clienteService){
        this.restTemplate = restTemplate;
        this.clienteService = clienteService;
    }

    @GetMapping("/listar-clientes")
    public ResponseEntity<String> listarClientes(){
        ResponseEntity<String> response = restTemplate.getForEntity(API_URL, String.class);

        return response;
    }

   @GetMapping("clientes/{id}")
    public ResponseEntity<String> pesquisarId(@PathVariable String id){
        ResponseEntity<String> response = restTemplate.getForEntity(API_URL, String.class);
        String respostaTratada = clienteService.pesquisarId(response, id);

        return ResponseEntity.ok(respostaTratada);
    }

    @GetMapping("clientes")
    public ResponseEntity<String> pesquisarCpf(@RequestParam("cpf") String cpf){
        ResponseEntity<String> response = restTemplate.getForEntity(API_URL, String.class);
        String respostaTratada = clienteService.pesquisarCpf(response, cpf);

        return ResponseEntity.ok(respostaTratada);
    }

}
