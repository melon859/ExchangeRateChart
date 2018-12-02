package model;

public class Exchange {
	
	public Exchange() {
	}
	
	public Exchange(String year, String month, String day, String deal, double price) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.deal = deal;
		this.price = price;
	}
	
	private String year;
	
	private String month;
	
	private String day;
	
	private String deal;
	
	private double price;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDeal() {
		return deal;
	}

	public void setDeal(String deal) {
		this.deal = deal;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Exchange [year=" + year + ", month=" + month + ", day=" + day + ", deal=" + deal + ", price=" + price
				+ "]";
	}
	
	
	

}
