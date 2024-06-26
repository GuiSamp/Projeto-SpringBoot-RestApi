package com.Apollo.apiresttemplate.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

            }catch(IOException e){
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

            }catch(IOException e){
                e.printStackTrace(); 
            }
        }
        return "";
    }

    public String buscarTotal(ResponseEntity<String> response){
        if(response != null && response.getBody() != null){
            try{
                JsonNode node = objectMapper.readTree(response.getBody());

                if(node.isArray()){
                    ObjectNode responseObject = objectMapper.createObjectNode();
                    responseObject.put("totalClientes", node.size());
                    return responseObject.toString();
                }

            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return "";
    }

    @SuppressWarnings("deprecation")
    public String buscarTipoPessoa(ResponseEntity<String> response){
        if(response != null && response.getBody() != null){
            try{
                JsonNode node = objectMapper.readTree(response.getBody());

                if(node.isArray()){
                    int totalFisica = 0;
                    int totalJuridica = 0;

                    for (JsonNode elemento : node) {
                        String tipoPessoa = elemento.get("tipoPessoa").asText();
                        if("F".equals(tipoPessoa)){
                            totalFisica++;
                        } else if("J".equals(tipoPessoa)){
                            totalJuridica++;
                        }
                    }

                    ObjectNode tipoPessoaoObject = objectMapper.createObjectNode();
                    tipoPessoaoObject.put("F", totalFisica);
                    tipoPessoaoObject.put("J", totalJuridica);

                    ObjectNode objectResponse = objectMapper.createObjectNode();
                    objectResponse.put("TipoPessoa", tipoPessoaoObject);

                    return objectResponse.toString();
                } 

            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return "";
    }

    @SuppressWarnings("deprecation")
    public String buscarEstado(ResponseEntity<String> response){
        if(response != null && response.getBody() != null){
            try{
                JsonNode node = objectMapper.readTree(response.getBody());

                if(node.isArray()){
                    Map<String, Integer> contagemEstados = new HashMap<>();

                    for(JsonNode elemento : node){
                        String estado = elemento.get("endereco").get("estado").asText();
                        contagemEstados.put(estado, contagemEstados.getOrDefault(estado, 0) + 1);
                    }

                    ObjectNode estadosObject = objectMapper.createObjectNode();

                    for(Map.Entry<String, Integer> entry : contagemEstados.entrySet()){
                        estadosObject.put(entry.getKey(), entry.getValue());
                    }

                    ObjectNode responsObject = objectMapper.createObjectNode();
                    responsObject.put("estados", estadosObject);
                    
                    return responsObject.toString();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    

        
}

    

