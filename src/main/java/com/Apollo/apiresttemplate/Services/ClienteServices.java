package com.Apollo.apiresttemplate.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

@Service
public class ClienteServices {

    ObjectMapper objectMapper = new ObjectMapper();
    
    public String pesquisarId(ResponseEntity<String> response, String idDesejado){
        if(response != null && response.getBody() != null){
            try{
                JsonNode node = objectMapper.readTree(response.getBody());
                if(node.isArray()){
                    for(JsonNode elemento : node){
                        if(elemento.get("id").asText().equals(idDesejado)){
                            return elemento.toString();
                        }
                    }
                }
            }catch(IOException e) {
                e.printStackTrace(); 
            }
        }
        return "";
    }

    public String pesquisarCpf(ResponseEntity<String> response, String cpfDesejado){
        if(response != null && response.getBody() != null){
            try{
                JsonNode node = objectMapper.readTree(response.getBody());
                if(node.isArray()){
                    for(JsonNode elemento : node){
                        if(elemento.get("cpfCnpj").asText().equals(cpfDesejado)){
                            return elemento.toString();
                        }
                    }
                }
            }catch(IOException e) {
                e.printStackTrace(); 
            }
        }
        return "";
    }
        
}

    

