package nl.kpn.jsonfeed.model.enums;

public enum Status
{
    PLANNED("Gepland"), ACTUAL("Actueel"), SOLVED("Opgelost");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}