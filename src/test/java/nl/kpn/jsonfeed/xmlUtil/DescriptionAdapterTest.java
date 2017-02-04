package nl.kpn.jsonfeed.xmlUtil;

import junit.framework.TestCase;
import nl.kpn.jsonfeed.model.Item;
import nl.kpn.jsonfeed.model.enums.Status;
import org.junit.Test;

public class DescriptionAdapterTest extends TestCase {

    private static final String XML_DESCRIPTION = "Als gevolg van een storing kunt u mogelijk in " +
            "enkele gebieden in de regio Amersfoort niet bij ons inloggen via het ADSL-netwerk. " +
            "Indien u telefonie of televisie via internet van ons afneemt dan kunnen deze diensten " +
            "door de storing verstoord zijn. Onze excuses voor dit ongemak. Wij stellen alles in het " +
            "werk om de storing zo spoedig mogelijk op te lossen.<br><br><br/>Starttijd: 2011-06-22 " +
            "11:00&nbsp;Eindtijd: onbekend&nbsp;";

    @Test
    public void testUnmarshal() throws Exception {
        DescriptionAdapter descriptionAdapter = new DescriptionAdapter();
        Item.Description description = descriptionAdapter.unmarshal(XML_DESCRIPTION);
        assertEquals(description.getDescription(), XML_DESCRIPTION);
        assertEquals(description.getEndDate(), "onbekend");
        assertEquals(description.getStartDate(), "2011-06-22 11:00");
        assertNotNull(description.getStatus());
        assertEquals(description.getStatus(), Status.ACTUAL.getName());
    }

    @Test
    public void testUnmarshalShouldWorkWıthEmptyDescription() throws Exception {
        DescriptionAdapter descriptionAdapter = new DescriptionAdapter();
        Item.Description description = descriptionAdapter.unmarshal(null);
        assertNull(description);
    }

    private static final String XML_DESCRIPTION_FOR_PLANNED = "Als gevolg van gepland onderhoud, " +
            "zullen werkzaamheden worden verricht aan de zender X. In verband met deze werkzaamheden " +
            "is de uitzending van de genoemde zender voor kortere of langere tijd onderbroken. " +
            "Onze excuses voor dit ongemak. Wij stellen alles in het werk om het onderhoud zo spoedig " +
            "mogelijk af te ronden.<br><br><br/>Starttijd: 2017-07-22 08:34&nbsp;Eindtijd: onbekend&nbsp;";

    @Test
    public void testUnmarshalForPlannedStatus() throws Exception {
        DescriptionAdapter descriptionAdapter = new DescriptionAdapter();
        Item.Description description = descriptionAdapter.unmarshal(XML_DESCRIPTION_FOR_PLANNED);
        assertEquals(description.getDescription(), XML_DESCRIPTION_FOR_PLANNED);
        assertEquals(description.getEndDate(), "onbekend");
        assertEquals(description.getStartDate(), "2017-07-22 08:34");
        assertNotNull(description.getStatus());
        assertEquals(description.getStatus(), Status.PLANNED.getName());
    }

    private static final String XML_DESCRIPTION_FOR_SOLVED = "Als gevolg van gepland onderhoud, zullen werkzaamheden" +
            " worden verricht aan de zender X. In verband met deze werkzaamheden was de uitzending van de genoemde" +
            " zender voor kortere of langere tijd onderbroken. Onze excuses voor dit ongemak. Het onderhoud hebben wij" +
            " inmiddels afgerond.<br><br><br/>Starttijd: 2015-07-22 08:39&nbsp;Eindtijd: 2015-07-22 08:45&nbsp;";
    @Test
    public void testUnmarshalForSolvedStatus() throws Exception {
        DescriptionAdapter descriptionAdapter = new DescriptionAdapter();
        Item.Description description = descriptionAdapter.unmarshal(XML_DESCRIPTION_FOR_SOLVED);
        assertEquals(description.getDescription(), XML_DESCRIPTION_FOR_SOLVED);
        assertEquals(description.getEndDate(), "2015-07-22 08:45");
        assertEquals(description.getStartDate(), "2015-07-22 08:39");
        assertNotNull(description.getStatus());
        assertEquals(description.getStatus(), Status.SOLVED.getName());
    }

    private static final String XML_DESCRIPTION_FOR_ACTUAL_WITH_END_DATE = "Als gevolg van gepland onderhoud, zullen werkzaamheden" +
            " worden verricht aan de zender X. In verband met deze werkzaamheden was de uitzending van de genoemde" +
            " zender voor kortere of langere tijd onderbroken. Onze excuses voor dit ongemak. Het onderhoud hebben wij" +
            " inmiddels afgerond.<br><br><br/>Starttijd: 2015-07-22 08:39&nbsp;Eindtijd: 2017-07-22 08:45&nbsp;";
    @Test
    public void testUnmarshalForActualStatus() throws Exception {
        DescriptionAdapter descriptionAdapter = new DescriptionAdapter();
        Item.Description description = descriptionAdapter.unmarshal(XML_DESCRIPTION_FOR_ACTUAL_WITH_END_DATE);
        assertEquals(description.getDescription(), XML_DESCRIPTION_FOR_ACTUAL_WITH_END_DATE);
        assertEquals(description.getEndDate(), "2017-07-22 08:45");
        assertEquals(description.getStartDate(), "2015-07-22 08:39");
        assertNotNull(description.getStatus());
        assertEquals(description.getStatus(), Status.ACTUAL.getName());
    }

    private static final String XML_DESCRIPTION_WITHOUT_STAR_DATE_TAG = "Als gevolg van gepland onderhoud, zullen werkzaamheden" +
            " worden verricht aan de zender X. In verband met deze werkzaamheden was de uitzending van de genoemde" +
            " zender voor kortere of langere tijd onderbroken. Onze excuses voor dit ongemak. Het onderhoud hebben wij" +
            " inmiddels afgerond.<br><br><br/>2015-07-22 08:39&nbsp;Eindtijd: 2017-07-22 08:45&nbsp;";

    @Test
    public void testUnmarshalShouldWorkWithoutStartDateTag() throws Exception {
        DescriptionAdapter descriptionAdapter = new DescriptionAdapter();
        Item.Description description = descriptionAdapter.unmarshal(XML_DESCRIPTION_WITHOUT_STAR_DATE_TAG);
        assertEquals(description.getDescription(), XML_DESCRIPTION_WITHOUT_STAR_DATE_TAG);
        assertEquals(description.getEndDate(), "2017-07-22 08:45");
        assertNull(description.getStartDate());
        assertNull(description.getStatus());
    }

    private static final String XML_DESCRIPTION_WITHOUT_END_DATE_TAG = "Als gevolg van gepland onderhoud, zullen werkzaamheden" +
            " worden verricht aan de zender X. In verband met deze werkzaamheden was de uitzending van de genoemde" +
            " zender voor kortere of langere tijd onderbroken. Onze excuses voor dit ongemak. Het onderhoud hebben wij" +
            " inmiddels afgerond.<br><br><br/>Starttijd: 2015-07-22 08:39&nbsp;Eindtijd:dd 2017-07-22 08:45&nbsp;";

    @Test
    public void testUnmarshalShouldWorkWithoutEndDateTag() throws Exception {
        DescriptionAdapter descriptionAdapter = new DescriptionAdapter();
        Item.Description description = descriptionAdapter.unmarshal(XML_DESCRIPTION_WITHOUT_END_DATE_TAG);
        assertEquals(description.getDescription(), XML_DESCRIPTION_WITHOUT_END_DATE_TAG);
        assertEquals(description.getStartDate(), "2015-07-22 08:39");
        assertNull(description.getEndDate());
        assertNull(description.getStatus());
    }
}