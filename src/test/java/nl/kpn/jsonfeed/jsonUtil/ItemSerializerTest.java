package nl.kpn.jsonfeed.jsonUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junit.framework.TestCase;
import nl.kpn.jsonfeed.model.Item;
import org.junit.Test;

/**
 * Tests custom Item object serializer
 * {@link nl.kpn.jsonfeed.jsonUtil.ItemSerializer}.
 */
public class ItemSerializerTest extends TestCase {

    private Gson gson;

    /**
     * Creates a GsonBuilder instance that can be used to build Gson with custom serializer configuration
     * @throws Exception
     */
    @Override
    public void setUp() throws Exception {
        gson = new GsonBuilder().
                setPrettyPrinting().
                registerTypeAdapter(Item.class, new ItemSerializer()).
                create();
    }

    private final static String EXPECTED_JSON = "{\n" +
            "  \"endDate\": \"end Date\",\n" +
            "  \"title\": \"title\",\n" +
            "  \"postalCodes\": \"postalCodes\",\n" +
            "  \"status\": \"status\",\n" +
            "  \"startDate\": \"start Date\",\n" +
            "  \"description\": \"description\"\n" +
            "}";

    @Test
    public void testCanSerializeTheItemObject() throws Exception {

        Item.Description description = (new Item()).new Description();
        description.setDescription("description");
        description.setEndDate("end Date");
        description.setStartDate("start Date");
        description.setStatus("status");
        Item item = new Item("title", null, null, "postalCodes", null, null, null, description);
        String json = gson.toJson(item);

        assertEquals(json, EXPECTED_JSON);
    }
    private final static String EXPECTED_JSON_WITHOUT_DESCRIPTION = "{\n" +
            "  \"title\": \"title\",\n" +
            "  \"postalCodes\": \"postalCodes\"\n" +
            "}";

    @Test
    public void testCanSerializeTheItemObjectWithEmptyDescription() throws Exception {
        Item item = new Item("title", null, null, "postalCodes", null, null, null, null);
        String json = gson.toJson(item);

        assertEquals(json, EXPECTED_JSON_WITHOUT_DESCRIPTION);
    }
}