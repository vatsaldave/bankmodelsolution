package httphunt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OutputRequest3 {
    private List<ToolSorted> toolsSortedOnUsage;

    public List<ToolSorted> getToolsSortedOnUsage() {
        return toolsSortedOnUsage;
    }

    public void setToolsSortedOnUsage(List<ToolSorted> toolsSortedOnUsage) {
        this.toolsSortedOnUsage = toolsSortedOnUsage;
    }


}
