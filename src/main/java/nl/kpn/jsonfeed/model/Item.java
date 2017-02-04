package nl.kpn.jsonfeed.model;

import lombok.Data;
import lombok.Getter;
import nl.kpn.jsonfeed.xmlUtil.DescriptionAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement( name = "item")
public class Item {

    @Getter
    @XmlElement(name="title")
    private String title;

    @Getter
    @XmlElement(name="category")
    private String category;

    @Getter
    @XmlElement(name="ticketNumber", namespace="http://www.sqills.com/james/")
    private String ticketNumber;

    @Getter
    @XmlElement(name="postalCodes", namespace="http://www.sqills.com/james/")
    private String postalCodes;

    @Getter
    @XmlElement(name="expectedEndDate", namespace="http://www.sqills.com/james/")
    private String expectedEndDate;

    @Getter
    @XmlElement(name="category", namespace="http://www.sqills.com/james/")
    private String category2;

    @Getter
    @XmlElement(name="locations", namespace="http://www.sqills.com/james/")
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