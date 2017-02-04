package nl.kpn.jsonfeed.xmlUtil;

import nl.kpn.jsonfeed.model.Item;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DescriptionAdapter extends XmlAdapter<String, Item.Description> {
    @Override
    public Item.Description unmarshal(String v) throws Exception {
        Item.Description description = (new Item()).new Description();
        description.setDescription(v);
        if (v != null && !v.isEmpty()) {
            description.setEndDate(findTag(v, "Eindtijd:"));
            description.setStartDate(findTag(v, "Starttijd:"));
            description.setStatus(calculateStatus(description.getStartDate(), description.getEndDate()));
        }
        return description;
    }

    @Override
    public String marshal(Item.Description v) throws Exception {
        return v.getDescription();
    }

    private String findTag(String description, String tag) {
        Pattern pattern = Pattern.compile("(?<=" + tag + " \\b).*?(?=" + "&nbsp" + ")");
        Matcher matcher = pattern.matcher(description);
        return matcher.find() ? matcher.group().trim() : null;
    }

    private String calculateStatus(String start, String end) {
        String status = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(start, formatter);
        LocalDateTime now = LocalDateTime.now();
        if (startDate.isAfter(now)) {
            status = "Gepland";
        } else if (end.equalsIgnoreCase("onbekend")) {
            status = "Actueel";
        } else {
            LocalDateTime endDate = LocalDateTime.parse(end, formatter);
            if (endDate.isBefore(now)) {
                status = "Opgelost";
            } else if (endDate.isAfter(now)) {
                status = "Actueel";
            }
        }
        return status;
    }
}