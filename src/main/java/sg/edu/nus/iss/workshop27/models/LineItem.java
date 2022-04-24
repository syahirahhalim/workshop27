package sg.edu.nus.iss.workshop27.models;

import jakarta.json.JsonObject;

public class LineItem {
    private Integer itemId;
    private String description;
    private Integer quantity;
    private double unitPrice;

    public Integer getItemId() {
        return this.itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }


    public static LineItem create(JsonObject o) {
        final LineItem li = new LineItem();
        li.setItemId(o.getInt("itemId"));
        li.setDescription(o.getString("description"));
        li.setQuantity(o.getInt("quantity"));
        li.setUnitPrice((double)o.getJsonNumber("unitPrice").doubleValue());
        return li;
    }
    
}
