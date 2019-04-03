package httphunt.dto;

public class Tools implements Comparable<Tools> {

    private String name;
    private int weight;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int compareTo(Tools o) {
        return Integer.valueOf(this.value).compareTo(Integer.valueOf(o.value));
    }
}
