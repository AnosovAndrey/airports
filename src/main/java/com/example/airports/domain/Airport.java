package com.example.airports.domain;

import com.opencsv.bean.CsvBindByPosition;

import java.util.stream.IntStream;

public class Airport {
    @CsvBindByPosition(position = 0)
    private int column1;
    @CsvBindByPosition(position = 1)
    private String column2;
    @CsvBindByPosition(position = 2)
    private String column3;
    @CsvBindByPosition(position = 3)
    private String column4;
    @CsvBindByPosition(position = 4)
    private String column5;
    @CsvBindByPosition(position = 5)
    private String column6;
    @CsvBindByPosition(position = 6)
    private double column7;
    @CsvBindByPosition(position = 7)
    private double column8;
    @CsvBindByPosition(position = 8)
    private int column9;
    @CsvBindByPosition(position = 9)
    private String column10;
    @CsvBindByPosition(position = 10)
    private String column11;
    @CsvBindByPosition(position = 11)
    private String column12;
    @CsvBindByPosition(position = 12)
    private String column13;
    @CsvBindByPosition(position = 13)
    private String column14;

    public Airport(String[] record) {
        this.column1 = Integer.parseInt(record[0]);
        this.column2 = record[1];
        this.column3 = record[2];
        this.column4 = record[3];
        this.column5 = record[4];
        this.column6 = record[5];
        this.column7 = Double.parseDouble(record[6]);
        this.column8 = Double.parseDouble(record[7]);
        this.column9 = Integer.parseInt(record[8]);
        this.column10 = record[9];
        this.column11 = record[10];
        this.column12 = record[11];
        this.column13 = record[12];
        this.column14 = record[13];
    }

    public String getColumn(int column){
        switch (column) {
            case 1:
                return Integer.toString(column1);
            case 2:
                return column2;
            case 3:
                return column3;
            case 4:
                return column4;
            case 5:
                return column5;
            case 6:
                return column6;
            case 7:
                return Double.toString(column7);
            case 8:
                return Double.toString(column8);
            case 9:
                return Integer.toString(column9);
            case 10:
                return column10;
            case 11:
                return column11;
            case 12:
                return column12;
            case 13:
                return column13;
            case 14:
                return column14;
            default:
                return column2;
        }
    }

    public String getColumn2() {
        return column2;
    }

    public String toString(int column) {
        StringBuilder sb = new StringBuilder();
        sb.append(getColumn(column)).append(" ");
        IntStream.range(1, 14).forEach($ -> {
            if($ != column)
                sb.append(getColumn($)).append(" ");
        });
        return sb.toString();
    }
}
