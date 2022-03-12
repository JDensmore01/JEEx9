/**
 * 
 */
package edu.nbcc.model;

/**
 * @author jason
 *
 */
public class BookModel {
	private Book book = new Book();
	private final int[] terms = new int[] {1, 2, 3, 4, 5, 6};
	
	public BookModel() {
		
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int[] getTerms() {
		return terms;
	}
	
	
}
