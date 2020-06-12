public class ComboItem {
    String date = "";
    public ComboItem(String date) {
        this.date = date;
    }
    @Override
    public String toString()
    {
        return date;
    }

    public String getDate()
    {
        return date;
    }
}
