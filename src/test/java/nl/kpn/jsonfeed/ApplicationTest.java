package nl.kpn.jsonfeed;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import junit.framework.TestCase;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.FileReader;

/**
 * Tests generated json files
 * {@link nl.kpn.jsonfeed.Application}
 */
public class ApplicationTest extends TestCase {

    private static final String BUSINESS_RESULT_EXPECTED_PATH = "src/test/resources/business_outages.json";
    private static final String BUSINESS_RESULT_GENERATED_PATH = "src/main/resources/business_outages.json";

    @Test
    public void testMainShouldGenerateSameBusinessJsonFileAsExpected() throws Exception {
        JsonParser parser = new JsonParser();
        try (FileReader fileReaderGenerated = new FileReader(BUSINESS_RESULT_GENERATED_PATH);
             FileReader fileReaderExpected = new FileReader(BUSINESS_RESULT_EXPECTED_PATH)) {
            JsonElement generatedJson = parser.parse(fileReaderGenerated);
            JsonElement expectedJson = parser.parse(fileReaderExpected);
            JSONAssert.assertEquals(expectedJson.toString(), generatedJson.toString(), JSONCompareMode.STRICT);
        }
    }

    private static final String CUSTOMER_RESULT_EXPECTED_PATH = "src/test/resources/customer_outages.json";
    private static final String CUSTOMER_RESULT_GENERATED_PATH = "src/main/resources/customer_outages.json";

    @Test
    public void testMainShouldGenerateSameCustomerJsonFileAsExpected() throws Exception {
        JsonParser parser = new JsonParser();
        try (FileReader fileReaderGenerated = new FileReader(CUSTOMER_RESULT_GENERATED_PATH);
             FileReader fileReaderExpected = new FileReader(CUSTOMER_RESULT_EXPECTED_PATH)) {
            JsonElement generatedJson = parser.parse(fileReaderGenerated);
            JsonElement expectedJson = parser.parse(fileReaderExpected);
            JSONAssert.assertEquals(expectedJson.toString(), generatedJson.toString(), JSONCompareMode.STRICT);
        }
    }
}