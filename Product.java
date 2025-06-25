/* This class for creating Product. */

import java.util.ArrayList;
import java.util.Calendar;

public class Product {

	private String productName = "";
	private Calendar saleDate;
	private double price;
	
	public Product(String sName, Calendar sDate, double price) {
		setProductName(sName);
		setSaleDate(sDate);
		setPrice(price);
	}
	
	// Checks if the asked product is in array list or not. Created for make easier exception handling.
	public static boolean isProductExist(ArrayList<Product> products, String product) {
		for(Product p: products) {
			if(p.getProductName().equals(product))
				return true;
		}
		return false;
	}
	
	public boolean isNotValidProduct() {
		if (productName.equals(""))
			return true;
		else return false;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		try {
			if (productName.length() >= 3)
				this.productName = productName;
			else throw new Exception("Invalid product name.");
		} catch (Exception ex) {
			System.out.println("Invalid product name: " + productName);
		}
	}

	public Calendar getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Calendar saleDate) {
		this.saleDate = saleDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", transactionDate=" + Test.calendarToString(saleDate) + ", price=" + price + "]";
	}
	
}
