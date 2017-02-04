package nl.kpn.jsonfeed.xmlUtil;

import nl.kpn.jsonfeed.model.Item;
import nl.kpn.jsonfeed.model.enums.Status;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DescriptionAdapter extends XmlAdapter<String, Item.Description> {
    private final static String TAG_START_DATE = "Starttijd:";
    private final static String TAG_END_DATE = "Eindtijd:";
    private final static String TAG_END = "&nbsp";
    private final static String DATE_PATTERN = "yyyy-MM-dd HH:mm";
    private final static String UNKNOWN = "onbekend";

    @Override
    public Item.Description unmarshal(String xmlString) throws Exception {
        Item.Description description = null;
        if (StringUtils.isNoneBlank(xmlString)) {
            description = (new Item()).new Description();
            description.setDescription(xmlString);
            description.setEndDate(findTag(xmlString, TAG_END_DATE));
            description.setStartDate(findTag(xmlString, TAG_START_DATE));
            description.setStatus(calculateStatus(description.getStartDate(), description.getEndDate()));
        }
        return description;
    }

    @Override
    public String marshal(Item.Description modelObject) throws Exception {
        return modelObject.getDescription();
    }

    private String findTag(String description, String tag) {
        Pattern pattern = Pattern.compile("(?<=" + tag + " \\b).*?(?=" + TAG_END + ")");
        Matcher matcher = pattern.matcher(description);
        return matcher.find() ? matcher.group().trim() : null;
    }

    private String calculateStatus(String start, String end) {
        String status = null;
        if(StringUtils.isNoneBlank(start)){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            LocalDateTime startDate = LocalDateTime.parse(start, formatter);
            LocalDateTime now = LocalDateTime.now();
            if (startDate.isAfter(now)) {
                status = Status.PLANNED.getName();
            } else if (StringUtils.equalsIgnoreCase(end, UNKNOWN)) {
                status = Status.ACTUAL.getName();
            } else if (StringUtils.isNoneBlank(end)){
                LocalDateTime endDate = LocalDateTime.parse(end, formatter);
                if (endDate.isBefore(now)) {
                    status = Status.SOLVED.getName();
                } else if (endDate.isAfter(now)) {
                    status = Status.ACTUAL.getName();
                }
            }
        }
        return status;
    }
}