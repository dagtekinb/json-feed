package nl.kpn.jsonfeed;

import nl.kpn.jsonfeed.model.Channel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Application {
    public static void main(String[] args) throws Exception {
        Channel channel = getObjectFromXml();
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
}