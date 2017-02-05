package nl.kpn.jsonfeed;

import nl.kpn.jsonfeed.jsonUtil.ItemJsonConverter;
import nl.kpn.jsonfeed.model.Channel;
import nl.kpn.jsonfeed.xmlUtil.ChannelXmlUnmarshaller;

public class Application {
    private static final String XML_PATH = "src/test/resources/outages.xml";

    private static final String BUSINESS_RESULT_PATH = "src/main/resources/business_outages.json";
    private static final String CUSTOMER_RESULT_PATH = "src/main/resources/customer_outages.json";

    public static void main(String[] args) throws Exception {
        ChannelXmlUnmarshaller xmlConverter = new ChannelXmlUnmarshaller();
        Channel channel = xmlConverter.toChannelObject(XML_PATH);
        ItemJsonConverter jsonConverter = new ItemJsonConverter();
        jsonConverter.toJsonFile(channel.getItems(), BUSINESS_RESULT_PATH, CUSTOMER_RESULT_PATH);
    }
}