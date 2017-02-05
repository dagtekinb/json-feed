package nl.kpn.jsonfeed.xmlUtil;

import nl.kpn.jsonfeed.model.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * The ChannelXmlUnmarshaller class governs the process of deserializing XML
 * data into newly created Channel object
 */
public class ChannelXmlUnmarshaller {
    private static final Logger logger = LoggerFactory.getLogger(ChannelXmlUnmarshaller.class);

    public Channel toChannelObject(String xmlPath) throws Exception {
        try {
            File file = new File(xmlPath);
            JAXBContext jaxbContext = jaxbContext = JAXBContext.newInstance(Channel.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Channel) jaxbUnmarshaller.unmarshal(file);
        } catch (Exception e) {
            logger.error("error occurred while unmarshalling XML file to the Channel object", e);
            throw e;
        }
    }
}
