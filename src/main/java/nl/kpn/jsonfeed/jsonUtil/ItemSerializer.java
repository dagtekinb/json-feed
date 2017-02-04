package nl.kpn.jsonfeed.jsonUtil;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import nl.kpn.jsonfeed.model.Item;

import java.lang.reflect.Type;

public class ItemSerializer implements JsonSerializer<Item> {
    @Override
    public JsonElement serialize(Item item, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonElement = new JsonObject();
        jsonElement.addProperty("endDate", item.getDescription().getEndDate());
        jsonElement.addProperty("title", item.getTitle());
        jsonElement.addProperty("postalCodes", item.getPostalCodes());
        jsonElement.addProperty("status", item.getDescription().getStatus());
        jsonElement.addProperty("startDate", item.getDescription().getStartDate());
        jsonElement.addProperty("description", item.getDescription().getDescription());
        return jsonElement;
    }
}