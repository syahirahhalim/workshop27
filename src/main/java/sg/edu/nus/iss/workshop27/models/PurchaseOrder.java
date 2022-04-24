package sg.edu.nus.iss.workshop27.models;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class PurchaseOrder {

    private Integer orderId;
    private String name;
    private String email;
    private List<LineItem> lineItems = new LinkedList<>();

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<LineItem> getLineItems() {
        return this.lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public static PurchaseOrder create(String jsonStr) throws Exception {
        JsonReader reader = Json.createReader(
            new ByteArrayInputStream(jsonStr.getBytes()));
        return create(reader.readObject());
    }

    private static PurchaseOrder create(JsonObject readObject) {
        final PurchaseOrder po =new PurchaseOrder();
        po.setName(readObject.getString("name"));
        po.setEmail(readObject.getString("email"));
        List<LineItem> lineItems = readObject.getJsonArray("lineitems")
            .stream()
            .map(li -> LineItem.create((JsonObject)li))
            .toList();
        po.setLineItems(lineItems);
        return po;
    }

}