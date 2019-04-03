package httphunt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OutputRequest2 {
    private List<String> toolsFound;

    public List<String> getToolsFound() {
        return toolsFound;
    }

    public void setToolsFound(List<String> toolsFound) {
        this.toolsFound = toolsFound;
    }
}
