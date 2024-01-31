package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.FlightStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private String no;
    private int departure;
    private String from;
    private String to;
    private int duration;
    private FlightStatus flightStatus = FlightStatus.UNDEFINED;
}
