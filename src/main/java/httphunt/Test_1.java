package httphunt;

import com.fasterxml.jackson.databind.ObjectMapper;
import httphunt.dto.InputResponse1;
import httphunt.dto.OutputRequest1;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Test_1 {
    private static String challenge = "https://http-hunt.thoughtworks-labs.net/challenge";
    private static String input = "https://http-hunt.thoughtworks-labs.net/challenge/input";
    private static String output = "https://http-hunt.thoughtworks-labs.net/challenge/output";
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        System.out.println("Start");
        try {

            InputResponse1 resp = new InputResponse1();
            resp.setEncryptedMessage("encrypted msg");
            resp.setKey("4");

            OutputRequest1 req = new OutputRequest1();
            req.setMessage("message");


            System.out.println(mapper.writeValueAsString(resp));

            System.out.println(mapper.writeValueAsString(req));


            resp = (InputResponse1) executeForResponse(input, InputResponse1.class, null);
            String encryptedMsg = resp.getEncryptedMessage();
            int offset = Integer.valueOf(resp.getKey());
            req.setMessage(Utils1.getNewString(encryptedMsg, offset));
            System.out.println("req.getMessage()-->" + req.getMessage());
            String outputResponse = (String) executeForResponse(output, String.class, mapper.writeValueAsString(req));
            System.out.println("outputResponse-->" + outputResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    private static Object executeForResponse(String url, Class responseType, String request) {

        String jsonStr = "";
        if (request != null) {
            jsonStr = executeRequest(url, HttpMethod.POST, new HttpEntity(request, createHeaders()));
        } else {
            jsonStr = executeRequest(url, HttpMethod.GET, new HttpEntity(createHeaders()));
        }
        System.out.println("jsonStr-->" + jsonStr);
        if (String.class == responseType) {
            return jsonStr;
        }
        try {
            return mapper.readValue(jsonStr, responseType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String executeRequest(String url, HttpMethod httpMethod, HttpEntity httpEntity) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, httpMethod, httpEntity, String.class);
        return responseEntity.getBody();
    }

    private static HttpHeaders createHeaders() {
        return new HttpHeaders() {
            {
                set("userId", "gdDHUyvGr");
                set("Content-Type", "application/json");
            }
        };
    }


}
