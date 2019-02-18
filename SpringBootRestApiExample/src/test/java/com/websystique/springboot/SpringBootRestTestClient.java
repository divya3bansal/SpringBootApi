package com.websystique.springboot;
 
import java.util.List;

import org.springframework.web.client.RestTemplate;
 

public class SpringBootRestTestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/SpringBootRestApi/api";
     
    /* GET */
    @SuppressWarnings("unchecked")
    private static void getFiboNumbers(){
        System.out.println("Testing fibo API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<String> numberList = restTemplate.getForObject(REST_SERVICE_URI+"/fibo/5", List.class);
         
        if(numberList!=null){
            for(String number : numberList){
            	System.out.println(number);
            }
        }
    }
     
    /* GET */
    @SuppressWarnings("unchecked")
    private static void getFiboSorted(){
        System.out.println("Testing fibo sorted API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<String> numberList = restTemplate.getForObject(REST_SERVICE_URI+"/fibosorted", List.class);
         
        if(numberList!=null){
            for(String number : numberList){
            	System.out.println(number);
            }
        }
    }
    
    public static void main(String args[]){
    	getFiboNumbers();
    	getFiboSorted();
    }
}