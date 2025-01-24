package com.vonage.weatherapi.services.messageService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Value("${vonage.sms.endpoint}")
    String url;

    @Value("${vonage.api.key}")
    String api_key;

    @Value("${vonage.api.secret}")
    String api_secret;

    @Value("${message.brand-name}")
    String brandName;

    @Value("${message.default-number}")
    String defaultMobileNumber;

    @Autowired
    RestTemplate restTemplate;

    @Value("${vonage.sms.is-required}")
    boolean isSmsRequired;



    @Override
    public void sendMessage(String mobileNumber, String message) {

        System.out.println("message - "+message+" sent to - "+mobileNumber);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("from", brandName);
        requestBody.put("text", message);

//        can be modified to mobileNumber variable
        requestBody.put("to", defaultMobileNumber);

        requestBody.put("api_key", api_key);
        requestBody.put("api_secret", api_secret);

        try{

            if(isSmsRequired)
            {
                System.out.println("Sending message to the user");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
                ResponseEntity<String> response = restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        requestEntity,
                        String.class
                );
                /*{

                check message error case and handle that case
                    "message-count":"1",
                        "messages":[
                    {
                        "to":"917416476714",
                            "message-id":"e7b0d156-08d9-488d-842f-1c53f099add2",
                            "status":"0",
                            "remaining-balance":"21.29428000",
                            "message-price":"0.07268000",
                            "network":"405025"
                    }]

                }

                 */

                ObjectMapper om = new ObjectMapper();
                JsonNode jsonResponse = om.readTree(response.getBody());
                if(jsonResponse.get("messages").get("status").equals("0"))
                {
                    System.out.println("successfully sent the message to the user");
                }
                else
                {
                    System.out.println("Message send failed");
                    System.out.println(jsonResponse);
                }

            }
        }
        catch (Exception e){
            System.out.println("Some error occured in sending the message to the user");
            System.out.println(e.getMessage());
        }

    }
}
