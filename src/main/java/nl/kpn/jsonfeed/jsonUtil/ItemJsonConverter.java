package nl.kpn.jsonfeed.jsonUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.kpn.jsonfeed.model.Item;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The ItemJsonConverter class governs the process of serializing object
 * data into newly created JSON files
 */
public class ItemJsonConverter {
    public void toJsonFile(List<Item> items, String businessFilePath, String customerFilePath) throws IOException {
        Gson gson = new GsonBuilder().
                setPrettyPrinting().
                registerTypeAdapter(Item.class, new ItemSerializer()).
                create();
        try (FileWriter businessFileWriter =
                     new FileWriter(businessFilePath);
             FileWriter customerFileWriter =
                     new FileWriter(customerFilePath)) {
            Stream streamBusiness = items.stream().filter(item -> item.getLocations().matches(".*(ZMST|ZMOH).*"));
            gson.toJson(streamBusiness.collect(Collectors.toList()), businessFileWriter);
            Stream streamCustomer = items.stream().filter(item -> !item.getLocations().matches(".*(ZMST|ZMOH).*"));
            gson.toJson(streamCustomer.collect(Collectors.toList()), customerFileWriter);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
