package br.com.igbeni.redventuresbackend.models.request;

public class WidgetRequest {

    private String name;

    private String color;

    private String price;

    private Integer inventory;

    private boolean melts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public boolean isMelts() {
        return melts;
    }

    public void setMelts(boolean melts) {
        this.melts = melts;
    }
}
