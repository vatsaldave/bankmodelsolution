package httphunt;

import com.fasterxml.jackson.databind.ObjectMapper;
import httphunt.dto.InputResponse3;
import httphunt.dto.OutputRequest3;
import httphunt.dto.ToolSorted;
import httphunt.dto.ToolUsage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Test_3 {
    private static String challenge = "https://http-hunt.thoughtworks-labs.net/challenge";
    private static String input = "https://http-hunt.thoughtworks-labs.net/challenge/input";
    private static String output = "https://http-hunt.thoughtworks-labs.net/challenge/output";
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        System.out.println("Start");
        try {
            Map<String, ToolSorted> mapVal = new HashMap<String, ToolSorted>();
            InputResponse3 resp = new InputResponse3();

            OutputRequest3 req = new OutputRequest3();

            resp = (InputResponse3) executeForResponse(input, InputResponse3.class, null);
//            resp = (InputResponse3)readFile();
            for (ToolUsage tool : resp.getToolUsage()) {
                if (!mapVal.containsKey(tool.getName())) {
                    ToolSorted sorted = new ToolSorted();
                    sorted.setName(tool.getName());
                    mapVal.put(tool.getName(), sorted);
                }
                mapVal.get(tool.getName()).addTimeUsedInMinutes(Utils3.getDifference(tool.getUseStartTime(), tool.getUseEndTime()));
            }
            List<ToolSorted> val = new ArrayList<ToolSorted>();
            for (Map.Entry<String, ToolSorted> entry : mapVal.entrySet()) {
                val.add(entry.getValue());
            }
            Collections.sort(val, Collections.reverseOrder());
            req.setToolsSortedOnUsage(val);
            System.out.println(mapper.writeValueAsString(req.getToolsSortedOnUsage()));
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

    private static Object readFile() {
        StringBuilder retVal = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:/Temp/xyz.json.txt"));

            while (reader.ready()) {
                retVal.append(reader.readLine());
            }
            reader.close();

            return mapper.readValue(retVal.toString(), InputResponse3.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
