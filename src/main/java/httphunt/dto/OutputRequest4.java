package httphunt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OutputRequest4 {
    private List<String> toolsToTakeSorted;

    public List<String> getToolsToTakeSorted() {
        return toolsToTakeSorted;
    }

    public void setToolsToTakeSorted(List<String> toolsToTakeSorted) {
        this.toolsToTakeSorted = toolsToTakeSorted;
    }
}
