package org.example.entities;

import lombok.Data;

import java.util.List;

@Data
public class JSONData {
    private List<Flight> flights;
    private Forecast forecast;
}
