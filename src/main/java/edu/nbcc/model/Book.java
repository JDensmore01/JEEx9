/**
 * 
 */
package edu.nbcc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jason
 *
 */
public class Book {
	private int id;
	private String name;
	private double price;
	private int term;
	
	public Book(int id, String name, double price, int term) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.term = term;
	}
	
	public Book() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	
	private List<Book> mockData = new ArrayList<Book>();
	
	/**
	 * Builds the Mock Data property
	 */
	private void buildMockData() {
		this.mockData.add(new Book(1, "Book 1", 19.99, 1));
		this.mockData.add(new Book(2, "Book 2", 29.99, 2));
		this.mockData.add(new Book(3, "Book 3", 39.99, 3));
		this.mockData.add(new Book(4, "Book 4", 49.99, 4));
		this.mockData.add(new Book(5, "Book 5", 59.99, 5));
		this.mockData.add(new Book(6, "Book 6", 69.99, 6));
	}
	
	/**
	 * Build and then get the list of book MockData
	 * @return List<Book> The list of books
	 */
	public List<Book> getBooks() {
		buildMockData();
		return this.mockData;
	}
	
	/**
	 * Get a book from the mockData
	 * @param id - The ID of the book you want
	 * @return The book with the matching id or null if doesn't exist
	 */
	public Book getBook(int id) {
		List<Book> query = this.getBooks().stream().filter(b -> b.id == id).collect(Collectors.toList());
		if (query.size() > 0) {
			return query.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Dummy method for creating a book.
	 * @return Book object with the new id
	 */
	public Book Create() {
		this.id = this.mockData.size();
		return this;
	}
	
	/**
	 * Dummy Save method
	 * @return 1 if id exists or 0 if it doesn't
	 */
	public int saveBook() {
		if ( getBook(this.id) != null) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * Dummy delete method
	 * @return 1 if id exists and 0 if not
	 */
	public int deleteBook() {
		if (getBook(this.id) != null) {
			return 1;
		}
		return 0;
	}
}
