package nl.kpn.jsonfeed.xmlUtil;

import nl.kpn.jsonfeed.model.Channel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * The ChannelXmlUnmarshaller class governs the process of deserializing XML
 * data into newly created Channel object
 */
public class ChannelXmlUnmarshaller {

    public Channel toChannelObject(String xmlPath) throws Exception {
        try {
            File file = new File(xmlPath);
            JAXBContext jaxbContext = jaxbContext = JAXBContext.newInstance(Channel.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Channel) jaxbUnmarshaller.unmarshal(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
