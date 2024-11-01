package moneyMini;

public class Entry {
	private String date;
	private String type; 
	private String category; 
	private int amount;
	private String note; 

    public Entry(String date, String type, String category, int amount, String note) {
        this.date = date;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.note = note;
    }
    
    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %,d원 | %s", date, type, category, amount, note);
    }
}
