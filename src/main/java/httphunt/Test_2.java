package httphunt;

import com.fasterxml.jackson.databind.ObjectMapper;
import httphunt.dto.InputResponse2;
import httphunt.dto.OutputRequest2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class Test_2 {
    private static String challenge = "https://http-hunt.thoughtworks-labs.net/challenge";
    private static String input = "https://http-hunt.thoughtworks-labs.net/challenge/input";
    private static String output = "https://http-hunt.thoughtworks-labs.net/challenge/output";
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        System.out.println("Start");
        try {

            InputResponse2 resp = new InputResponse2();

            OutputRequest2 req = new OutputRequest2();
            req.setToolsFound(new ArrayList<String>());
            resp = (InputResponse2) executeForResponse(input, InputResponse2.class, null);
            List<String> superSet = Utils2.getListOfChars(resp.getHiddenTools());
            for (String str : resp.getTools()) {
                if (Utils2.containsAll(superSet, str)) {
                    req.getToolsFound().add(str);
                }
            }
            System.out.println("req.getToolsFound()-->" + req.getToolsFound());
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
