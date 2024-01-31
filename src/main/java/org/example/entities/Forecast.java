package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forecast {
    private List<TimeEntry> moscow;
    private List<TimeEntry> novosibirsk;
    private List<TimeEntry> omsk;
}
