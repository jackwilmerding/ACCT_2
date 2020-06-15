import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
    /*
    public HashMap<String, Patron> reader(String queryDate) throws FileNotFoundException {
        HashMap hm = new HashMap<String, Patron>();
        Scanner scan = new Scanner(new File(date()));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] data = line.split(",");
            hm.put(data[0], new Patron(data[1], data[2], data[0]));
        }
        return hm;
    }
    */
    public void writeLog(String date) throws IOException {
        File file = new File("log.csv");
        if(file.exists() && !file.isDirectory()) {
            FileWriter fwriter = new FileWriter("log.csv", true);
            fwriter.write("\n" + date);
            fwriter.flush();
            fwriter.close();
        }
        else {
            FileWriter fWriter = new FileWriter( "log.csv");
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
