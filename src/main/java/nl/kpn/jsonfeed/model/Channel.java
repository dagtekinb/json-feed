package nl.kpn.jsonfeed.model;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement( name = "rss" )
public class Channel {

    @XmlElementWrapper(name="channel")
    @XmlElement( name = "item" )
    @Getter
    private List<Item> items;
}