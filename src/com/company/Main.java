/*
 * Classname: Main
 * @version 24.06.2020
 * @author Ivanna Moskaliuk KNUTE
 * Home Task: Multi-threading.
 *  1. Use the file from the previous task  - logs.txt.
 *  2. Create a class that manages logs in this file.
 *  3. Create a method that finds all the ERROR logs
    for a specific date and write them into a specific file (name = ERROR  + Date  + .log)
 *  4. In your main class develop a functionality to
    create 5 such a files for 5 different days. Launch them in consistent way (one after another ).
 *  5. Repeat the above  task in parallel way. Multi-threading.
 *  6. Compare the results.
 */
package com.company;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {

    static class MyThread extends Thread {

        private String date;

        MyThread(String date){
            this.date = date;
        }

        // A method which initialize LogsManager class and calls its method "getErrorByDate"

        @Override
        public void run() {
            LogsService service = new LogsService();
            try {
                service.getLogsByDate(date);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {

        LogsService service1 = new LogsService();

        // @param startConsistent return time start consistent way
        LocalDateTime startConsistent = LocalDateTime.now();

        // creating 5 such a files for 5 different days for consistent way
        new MyThread("2019-09-19").start();
        new MyThread("2019-10-09").start();
        new MyThread("2019-11-11").start();
        new MyThread("2019-12-19").start();
        new MyThread("2020-01-01").start();

        // @param finishConsistent return time finish consistent way
        LocalDateTime finishConsistent = LocalDateTime.now();

        // @param consistentTime count expensive time
        long consistentTime = ChronoUnit.MILLIS.between(startConsistent, finishConsistent);

        System.out.println("TOTAL DURATION of threads: "
                + consistentTime + " milliseconds");

        System.out.println("-------------------------");

        // @param startParallel return time start parallel way
        LocalDateTime startParallel = LocalDateTime.now();

        // creating 5 such a files for 5 different days for parallel way

        service1.getLogsByDate("2019-09-19");
        service1.getLogsByDate("2019-10-09");
        service1.getLogsByDate("2019-11-11");
        service1.getLogsByDate("2019-12-19");
        service1.getLogsByDate("2020-01-01");

        // @param finishParallel return time finish parallel way
        LocalDateTime finishParallel = LocalDateTime.now();

        // @param parallelTime count expensive time
        long parallelTime = ChronoUnit.MILLIS.between(startParallel, finishParallel);

        System.out.println("TOTAL DURATION concequently: "
                + parallelTime + " milliseconds");

        /*
         * Comparing the duration results of two methods
         */
        if (consistentTime > parallelTime) {
            System.out.println("\nConsequent have time is best than Multi-Threading");
        } else if (consistentTime < parallelTime) {
            System.out.println("\n Multi-Threading have time is best than Consequent");
        } else {
            System.out.println("\nTime both methods is the same");
        }
    }
}
