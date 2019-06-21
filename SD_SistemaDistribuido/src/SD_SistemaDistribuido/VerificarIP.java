/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SD_SistemaDistribuido;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Jean Amorim
 */
public class VerificarIP {
    
     public static List<String> LISTA_DE_IPS = new ArrayList<String>();



     public boolean verificaIP(String newIp) {
        Map<String, List<String>> mapaIps = LISTA_DE_IPS.stream().collect(Collectors.groupingBy(String::toString));

        if (mapaIps.keySet().stream().noneMatch(key -> key.equals(newIp) && mapaIps.get(key).size() >= 5)) {
            LISTA_DE_IPS.add(newIp);
            return false;
        }

        return true;
    }
  
}
