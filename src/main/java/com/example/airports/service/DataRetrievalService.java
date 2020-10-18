package com.example.airports.service;

import com.example.airports.domain.Airport;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataRetrievalService {

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
            //airportsWithAttribute.forEach(x -> sb.append(x.toString(column)).append("\n"));
            sb.append("Количество найденных строк: ").append(airportsWithAttribute.size()).append("\n");
        }
        sb.append("Время затраченое на поиск: ").append(timeElapsed).append(" мс.");

        return sb.toString();
    }

    public void beanBuilderFromCsv() throws Exception {
        var fileName = "src/main/resources/airports.dat";
        Path myPath = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(myPath,
                StandardCharsets.UTF_8)) {

            ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();
            ms.setType(Airport.class);

            CsvToBean csvToBean = new CsvToBeanBuilder<Airport>(br)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withMappingStrategy(ms)
                    .withEscapeChar('\0')
                    .build();

            airports = csvToBean.parse();
            Collections.sort(airports, Comparator.comparing(Airport::getColumn2));
        }
    }
}
