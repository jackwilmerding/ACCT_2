import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.text.DateFormat;
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
    public HashMap<String, Patron> reader(String queryDate) {
        HashMap hm = new HashMap<String, Patron>();
        Scanner scan = new Scanner(date() + ".csv");
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] data = line.split(",");
            hm.put(data[0], new Patron(data[1], data[2], data[0]));
        }
        return hm;
    }
    public String readWholesale(String path) {
        Scanner scan = new Scanner(path);
        String csv = "";
        while(scan.hasNextLine()) {
            csv += scan.nextLine() + "\n";
        }
        csv = csv.substring(0, csv.length() - 1);
        return csv;
    }
    public void writer(String firstName, String lastName, String phone) throws IOException {
        File file = new File(date() + ".csv");
        if(file.exists() && !file.isDirectory()) {
            String csv = readWholesale(date() + ".csv");
            csv += "\n" + phone + "," + firstName + "," + lastName;
            FileWriter fwriter = new FileWriter(date() + ".csv", true);
            fwriter.write(csv);
            fwriter.flush();
            fwriter.close();
        }
        else {
            FileWriter fWriter = new FileWriter(date() + ".csv");
            fWriter.write(phone + "," + firstName + "," + lastName);
        }
        return;
    }
    public String[] search(String phone) {
        File[] files = new File("ACCT 2/").listFiles();
        ArrayList<String> dates = new ArrayList<String>();
        for(File file : files) {
            if (file.getName().endsWith(".csv")) {
                Scanner scan = new Scanner(file.getName());
                while(scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] data = line.split(",");
                    if (data[0].equals(phone)) {
                        dates.add(data[0]);
                    }
                }
            }
        }
        return dates.toArray(new String[dates.size()]);
    }
}
