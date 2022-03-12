package edu.nbcc.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.nbcc.model.Book;
import edu.nbcc.model.BookModel;
import edu.nbcc.model.ErrorModel;
import edu.nbcc.util.ValidationUtil;

/**
 * Servlet implementation class BookController
 */
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BOOK_VIEW = "/books.jsp";
	private static final String CREATE_BOOK = "/book.jsp";
	private static final String BOOK_SUMMARY = "/bookSummary.jsp";
	
	private RequestDispatcher view;
	
	public RequestDispatcher getView() {
		return view;
	}
	
	public void setView(HttpServletRequest request, String viewPath) {
		view = request.getRequestDispatcher(viewPath);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book book = new Book();
		
		String path = request.getPathInfo();
		if (path == null) {
			request.setAttribute("vm", book.getBooks());
			setView(request, BOOK_VIEW);
		} else {
			String[] pathComponents = path.split("/");
			if (pathComponents[1].equals("create") || ValidationUtil.isNumeric(pathComponents[1])) {
				int id = ValidationUtil.getInteger(pathComponents[1]);
				if (id != 0) {
					Book bk = book.getBook(id);
					if (bk != null) {
						BookModel vm = new BookModel();
						vm.setBook(bk);
						request.setAttribute("vm", vm);
					} else {
						request.setAttribute("error", new ErrorModel("Book not found"));
					}
				} else {
					request.setAttribute("vm", new BookModel());
				}
				setView(request, CREATE_BOOK);
			}
		}
		getView().forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<>();
		setView(request, BOOK_SUMMARY);
		Book bk = new Book();
		
		try {
			if (request.getParameter("bookPrice") == null || request.getParameter("bookPrice").trim() == "") {
				errors.add("Price is required");
			}
			
			if (request.getParameter("bookTerm") == null) {
				errors.add("Term is required");
			}
			
			if (request.getParameter("bookName") == null || request.getParameter("bookName").trim().equals("")) {
				errors.add("Name is required");
			}
			
			int id = ValidationUtil.getInteger(request.getParameter("hdnBookId"));
			String name = request.getParameter("bookName");
			int term = ValidationUtil.getTerm(request, "bookTerm", errors);
			double price = ValidationUtil.getDouble(request, "bookPrice", errors);
			
			if (price == 0.0) {
				errors.add("Price cannot be zero.");
			}
			
			if (errors.size() == 0) {
				bk.setId(id);
				bk.setName(name);
				bk.setTerm(term);
				bk.setPrice(price);
				
				String action = request.getParameter("action").toLowerCase();
				
				switch (action) {
					case "create":
						request.setAttribute("createdBook", bk.Create());
						break;
					case "save":
						if (bk.saveBook() > 0) {
							request.setAttribute("savedBook", bk);							
						} else {
							BookModel vm = new BookModel();
							vm.setBook(bk);
							request.setAttribute("vm", vm);
							request.setAttribute("error", new ErrorModel("Book does not exist"));
							setView(request, CREATE_BOOK);
						}
						break;
					case "delete":
						if (bk.deleteBook() > 0) {
							request.setAttribute("deletedBook", bk);
						} else {
							BookModel vm = new BookModel();
							vm.setBook(bk);
							request.setAttribute("vm", vm);
							request.setAttribute("error", new ErrorModel("Book does not exist"));
							setView(request, CREATE_BOOK);
						}
						break;
				} 
			} else {
				setView(request, CREATE_BOOK);
				ErrorModel errorModel = new ErrorModel();
				errorModel.setErrors(errors);
				request.setAttribute("errors", errorModel);
				request.setAttribute("vm", new BookModel());
			}

		} catch (Exception e) {
			setView(request, CREATE_BOOK);
			request.setAttribute("error", new ErrorModel("An error has occurred, please try again."));
		}
		
		getView().forward(request, response);
	}

}
