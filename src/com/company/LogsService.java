package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class LogsService {

    private String dateTime;

    public LogsService() {
    }

    public LogsService(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /*
     * A method, that must find a date and write in the file
     * @param String str - a date of searching in a format yyyy-mm-dd
     * @throw IOException
     */
    public void getLogsByDate(String str) throws IOException {

        // @param start return time start
        LocalDateTime start = LocalDateTime.now();

        System.out.println(str + " search is started at - " + start);

        /*
         * @param errorLinesList return list with ERROR logs by date
         */
        List<String> errorLinesList = Files.lines(Paths
                .get("C:\\Users\\ivank\\Desktop\\logs.txt"))
                .filter(line -> line.contains(str))
                .filter(line -> line.contains("ERROR"))
                .collect(Collectors.toList());

        /*
         * @param countLines return amount of logs
         */
        int countLines = errorLinesList.size();

        // @param finish return time finish
        LocalDateTime finish = LocalDateTime.now();

        // @param duration count expensive time
        long duration = ChronoUnit.MILLIS.between(start, finish);

        System.out.println("-------------------");

        System.out.println("There are " + countLines
                + " ERROR lines." + " on " + str);

        System.out.println("Execution time: " + duration);

        /*
         * @param stringData returns a value in String
         */
        String stringData = "";
        for (String line : errorLinesList) {
            stringData += line + "\n";
        }

        /*
         * @param directoryPath return directory for writing text
         */
        String directoryPath = "C:\\Users\\ivank\\Desktop\\ERROR-date "
                + str + ".txt";

        // write result to file
        Files.write(Paths.get(directoryPath), stringData.getBytes());

    }
}