package nl.kpn.jsonfeed.xmlUtil;

import junit.framework.TestCase;
import nl.kpn.jsonfeed.model.Channel;
import org.junit.Test;

/**
 * Tests XML file unmarshaller
 * {@link nl.kpn.jsonfeed.xmlUtil.ChannelXmlUnmarshaller}
 */
public class ChannelXmlUnmarshallerTest extends TestCase {

    @Test
    public void testToChannelObject() throws Exception {
        String xmlPath = "src/test/resources/outages.xml";
        ChannelXmlUnmarshaller unmarshaller = new ChannelXmlUnmarshaller();
        Channel channel = unmarshaller.toChannelObject(xmlPath);
        assertNotNull(channel);
        assertNotNull(channel.getItems());
        assertEquals(channel.getItems().size(), 123);
    }
}