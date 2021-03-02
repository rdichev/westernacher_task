package main.java.enumeration;

public enum LocalizationEnum {
    ENGLISH("English (US)"),
    BULGARIAN("Deutsch"),
    DEUTSCH("Deutsch");

    public String value;

    LocalizationEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
