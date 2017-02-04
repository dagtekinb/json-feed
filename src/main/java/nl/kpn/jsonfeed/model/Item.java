package nl.kpn.jsonfeed.model;

import lombok.Data;
import lombok.Getter;
import nl.kpn.jsonfeed.xmlUtil.DescriptionAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement( name = "item")
public class Item {

    private final static String NAMESPACE = "http://www.sqills.com/james/";

    @Getter
    @XmlElement(name="title")
    private String title;

    @Getter
    @XmlElement(name="category")
    private String category;

    @Getter
    @XmlElement(name="ticketNumber", namespace=NAMESPACE)
    private String ticketNumber;

    @Getter
    @XmlElement(name="postalCodes", namespace=NAMESPACE)
    private String postalCodes;

    @Getter
    @XmlElement(name="expectedEndDate", namespace=NAMESPACE)
    private String expectedEndDate;

    @Getter
    @XmlElement(name="category", namespace=NAMESPACE)
    private String category2;

    @Getter
    @XmlElement(name="locations", namespace=NAMESPACE)
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