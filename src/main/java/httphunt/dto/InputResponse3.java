package httphunt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InputResponse3 {
    private List<ToolUsage> toolUsage;

    public List<ToolUsage> getToolUsage() {
        return toolUsage;
    }

    public void setToolUsage(List<ToolUsage> toolUsage) {
        this.toolUsage = toolUsage;
    }

}