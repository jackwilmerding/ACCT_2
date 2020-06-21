/*
Active COVID Contact Tracing (ACCT) Gen 2 / A program designed for small businesses to prevent the spread of COVID-19
    Copyright (C) 2020 John Wilmerding

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.io.File;
import java.util.Calendar;
public class Backend {
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd";
    public static String date() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }
    public void writeLog(String date) throws IOException {
        File file = new File("log.csv");
        if(file.exists() && !file.isDirectory()) {
            FileWriter fwriter = new FileWriter(file, true);
            fwriter.write("\n" + date);
            fwriter.flush();
            fwriter.close();
        }
        else {
            file.createNewFile();
            FileWriter fWriter = new FileWriter( file);
            fWriter.write(date);
            fWriter.flush();
            fWriter.close();
        }
        return;
    }
    public boolean searchLog(String fileName) {
        boolean found = false;
        try {
            Scanner logSearcher = new Scanner(new File("log.csv"));
            while(logSearcher.hasNextLine()) {
                String line = logSearcher.nextLine();
                if(line.equals(fileName)) {
                    found = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return found;
    }
    public void writer(String firstName, String lastName, String email) throws IOException {
        File file = new File(date() + ".csv");
        if(file.exists() && !file.isDirectory()) {
            String csv = "\n" + email + "," + firstName + "," + lastName;
            FileWriter fwriter = new FileWriter(date() + ".csv", true);
            if ((csv != ",,") && (csv.split(",").length == 3)) {
                fwriter.write(csv);
            }
            fwriter.flush();
            fwriter.close();
        }
        else {
            FileWriter fWriter = new FileWriter(date() + ".csv");
            String info = email.toLowerCase() + "," + firstName + "," + lastName;
            if ((info != ",,") && (info.split(",").length == 3)) {
                fWriter.write(info);
            }
            fWriter.flush();
            fWriter.close();
        }
        return;
    }
    public String[] search(String email) {
        File dir = new File(".");
        File[] files = dir.listFiles();
        ArrayList<String> dateItems = new ArrayList<>();
        for(File file : files) {
            if (file != null && file.getName().endsWith(".csv")) {
                Scanner scan = null;
                try {
                    scan = new Scanner(new File(file.getName()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while(scan.hasNextLine()) {
                    String line = scan.nextLine();
                    if ((line != ",,") && (line.split(",").length == 3)){
                        String[] data = line.split(",");
                        if (data[0].toLowerCase().equals(email.toLowerCase())) {
                            dateItems.add(file.getName());
                        }
                    }
                }
            }
        }
        return dateItems.toArray(new String[dateItems.size()]);
    }
}
