package httphunt.dto;

public class ToolSorted implements Comparable<ToolSorted> {
    private long timeUsedInMinutes;
    private String name;

    public long getTimeUsedInMinutes() {
        return timeUsedInMinutes;
    }

    public void setTimeUsedInMinutes(long timeUsedInMinutes) {
        this.timeUsedInMinutes = timeUsedInMinutes;
    }

    public void addTimeUsedInMinutes(long timeUsedInMinutes) {
        this.timeUsedInMinutes += timeUsedInMinutes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(ToolSorted o) {
        return Long.valueOf(this.timeUsedInMinutes).compareTo(Long.valueOf(o.timeUsedInMinutes));
    }
}
