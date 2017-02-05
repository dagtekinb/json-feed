package nl.kpn.jsonfeed.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import nl.kpn.jsonfeed.xmlUtil.DescriptionAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement( name = "item")
public class Item {

    private final static String JAMES = "http://www.sqills.com/james/";

    public Item() {}

    public Item(String title, String category, String ticketNumber, String postalCodes, String expectedEndDate, String category2, String locations, Description description) {
        this.title = title;
        this.category = category;
        this.ticketNumber = ticketNumber;
        this.postalCodes = postalCodes;
        this.expectedEndDate = expectedEndDate;
        this.category2 = category2;
        this.locations = locations;
        this.description = description;
    }

    @Getter
    @XmlElement(name="title")
    private String title;

    @Getter
    @XmlElement(name="category")
    private String category;

    @Getter
    @XmlElement(name="ticketNumber", namespace= JAMES)
    private String ticketNumber;

    @Getter
    @XmlElement(name="postalCodes", namespace= JAMES)
    private String postalCodes;

    @Getter
    @XmlElement(name="expectedEndDate", namespace= JAMES)
    private String expectedEndDate;

    @Getter
    @XmlElement(name="category", namespace= JAMES)
    private String category2;

    @Getter
    @XmlElement(name="locations", namespace= JAMES)
    private String locations;

    @Getter
    @XmlElement(name="description")
    @XmlJavaTypeAdapter(DescriptionAdapter.class)
    private Description description;

    @Data
    public class Description {
        private String description;
        private String endDate;
        private String startDate;
        private String status;
    }
}