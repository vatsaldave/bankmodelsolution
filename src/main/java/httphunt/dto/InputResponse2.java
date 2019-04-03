package httphunt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InputResponse2 {
    private String hiddenTools;
    private List<String> tools;

    public String getHiddenTools() {
        return hiddenTools;
    }

    public void setHiddenTools(String hiddenTools) {
        this.hiddenTools = hiddenTools;
    }

    public List<String> getTools() {
        return tools;
    }

    public void setTools(List<String> tools) {
        this.tools = tools;
    }
}
