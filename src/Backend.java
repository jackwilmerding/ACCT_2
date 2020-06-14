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
    public void writer(String firstName, String lastName, String email) throws IOException {
        File file = new File(date() + ".csv");
        if(file.exists() && !file.isDirectory()) {
            String csv = "\n" + email + "," + firstName + "," + lastName;
            FileWriter fwriter = new FileWriter(date() + ".csv", true);
            fwriter.write(csv);
            fwriter.flush();
            fwriter.close();
        }
        else {
            FileWriter fWriter = new FileWriter(date() + ".csv");
            String info = email.toLowerCase() + "," + firstName + "," + lastName;
            fWriter.write(info);
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
                    String[] data = line.split(",");
                    System.out.println(data);
                    for(int i = 0; i < 3; i++) {
                        System.out.println(data[i]);
                    }
                    if (data[0].toLowerCase().equals(email.toLowerCase())) {
                        dateItems.add(file.getName());
                    }
                }
            }
        }
        for(int i = 0; i < dateItems.size(); i++) {
            System.out.println(dateItems.get(i));
        }
        return dateItems.toArray(new String[dateItems.size()]);
    }
}
