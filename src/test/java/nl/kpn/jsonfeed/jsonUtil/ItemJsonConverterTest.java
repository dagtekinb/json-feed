package nl.kpn.jsonfeed.jsonUtil;

import junit.framework.TestCase;
import nl.kpn.jsonfeed.model.Item;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests Item objects to JSON file conversion
 * {@link nl.kpn.jsonfeed.jsonUtil.ItemJsonConverter}
 */
public class ItemJsonConverterTest extends TestCase {
    private static final String BUSINESS_RESULT_PATH = "src/test/resources/test_business_outages.json";
    private static final String CUSTOMER_RESULT_PATH = "src/test/resources/test_customer_outages.json";

    List<Item> items;

    @Override
    public void setUp() throws Exception {
        items = new ArrayList();
        Item.Description description = (new Item()).new Description();
        description.setDescription("description");
        description.setEndDate("end Date");
        description.setStartDate("start Date");
        description.setStatus("status");
        Item item = new Item("title", "category", "ticketNumber", "postalCodes", "expectedEndDate", "category2", "ZMST", description);
        items.add(item);

        Item.Description description2 = (new Item()).new Description();
        description2.setDescription("description2");
        description2.setEndDate("end Date2");
        description2.setStartDate("start Date2");
        description2.setStatus("status2");
        Item item2 = new Item("title2", "category2", "ticketNumber2", "postalCodes2", "expectedEndDate2", "category22", "AMS", description2);
        items.add(item2);
    }

    @Test
    public void testToJsonFile() throws Exception {
        ItemJsonConverter jsonConverter = new ItemJsonConverter();
        jsonConverter.toJsonFile(items, BUSINESS_RESULT_PATH, CUSTOMER_RESULT_PATH);

        File file = new File(BUSINESS_RESULT_PATH);
        assertNotNull(file.exists());

        try(FileReader fileReader = new FileReader(BUSINESS_RESULT_PATH); BufferedReader bufferedReader = new BufferedReader(fileReader)){
            String sCurrentLine;
            int lineCount = 0;
            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                if(lineCount == 3)
                    assertEquals(sCurrentLine, "    \"title\": \"title\",");
                lineCount++;
            }
            assertEquals(lineCount, 10);
        }

        file = new File(CUSTOMER_RESULT_PATH);
        assertNotNull(file.exists());

        try(FileReader fileReader = new FileReader(CUSTOMER_RESULT_PATH); BufferedReader bufferedReader = new BufferedReader(fileReader)){
            String sCurrentLine;
            int lineCount = 0;
            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                if(lineCount == 3)
                    assertEquals(sCurrentLine, "    \"title\": \"title2\",");
                lineCount++;
            }
            assertEquals(lineCount, 10);
        }
    }
}