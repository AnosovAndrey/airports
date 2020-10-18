package com.example.airports.service;

import com.example.airports.domain.Airport;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataService {

    @Value("${column:2}")
    private static int column;

    private List<Airport> airports;

    public String getMessage() throws Exception {
        return getMessage(column);
    }

    public String getMessage(int column) throws Exception {
        this.column = column;
        beanBuilderFromCsv();
        return "Введите строку:";
    }

    public String getData(String string){
        long start = System.currentTimeMillis();

        List<Airport> airportsWithAttribute = airports.stream()
                .filter(airport -> airport.getColumn(column).startsWith(string))
                .collect(Collectors.toList());

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        StringBuilder sb = new StringBuilder();

        if(airportsWithAttribute.isEmpty()){
            sb.append("Количество найденных строк: 0\n");
        } else {
            airportsWithAttribute.forEach(x -> System.out.println(x.toString(column)));
            sb.append("Количество найденных строк: ").append(airportsWithAttribute.size()).append("\n");
        }
        sb.append("Время затраченое на поиск: ").append(timeElapsed).append(" мс.");

        return sb.toString();
    }

    public void beanBuilderFromCsv() throws Exception {

        airports = new ArrayList<Airport>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/airports.dat"));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                airports.add(new Airport(nextRecord));
            }
        }

        Collections.sort(airports, Comparator.comparing(Airport::getColumn2));
    }

}
