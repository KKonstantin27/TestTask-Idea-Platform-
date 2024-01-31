package org.example.enums;


public enum FlightStatus {
    SCHEDULED("по расписанию"), CANCELED("отменён"), UNDEFINED("не определён");
    private String textValue;

    FlightStatus(String textValue) {
        this.textValue = textValue;
    }

    public String getTextValue() {
        return textValue;
    }
}
