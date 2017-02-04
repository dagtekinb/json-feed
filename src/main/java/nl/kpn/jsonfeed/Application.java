package nl.kpn.jsonfeed;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.kpn.jsonfeed.jsonUtil.ItemSerializer;
import nl.kpn.jsonfeed.model.Channel;
import nl.kpn.jsonfeed.model.Item;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) throws Exception {
        Channel channel = getObjectFromXml();
        generateJsonFiles(channel);
    }

    private static Channel getObjectFromXml() throws JAXBException {
        File file = new File("src/test/resources/outages.xml");
        try {
            JAXBContext jaxbContext = jaxbContext = JAXBContext.newInstance(Channel.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Channel) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static void generateJsonFiles(Channel channel) throws IOException {
        Gson gson = new GsonBuilder().
                setPrettyPrinting().
                registerTypeAdapter(Item.class, new ItemSerializer()).
                create();
        try (FileWriter businessFileWriter =
                     new FileWriter("src/main/resources/business_outages.json");
             FileWriter customerFileWriter =
                     new FileWriter("src/main/resources/customer_outages.json")) {
            Stream streamBusiness = channel.getItems().stream().filter(item -> item.getLocations().matches(".*(ZMST|ZMOH).*"));
            gson.toJson(streamBusiness.collect(Collectors.toList()), businessFileWriter);
            Stream streamCustomer = channel.getItems().stream().filter(item -> !item.getLocations().matches(".*(ZMST|ZMOH).*"));
            gson.toJson(streamCustomer.collect(Collectors.toList()), customerFileWriter);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}