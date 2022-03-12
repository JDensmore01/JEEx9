<%-- 
    Document   : bookSummary
    Created on : Jan 28, 2020, 8:23:04 AM
    Author     : Chris.Cusack

    The view for the process book
    Support
    -Create
    -Saving changes
    -Delete
--%>


<%@page import="edu.nbcc.model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <title>Book</title>
    </head>
    
    <body class="p-4">
        <%@include file="WEB-INF/jspf/navigation.jspf" %>
        <h1>Book Summary</h1>
			<%
				Book book = null;
    			
				if (request.getAttribute("createdBook") != null) {
		    		book = (Book)request.getAttribute("createdBook");
		    	}
				if (request.getAttribute("savedBook") != null) {
		    		book = (Book)request.getAttribute("savedBook");
		    	}
				if (request.getAttribute("deletedBook") != null) {
		    		book = (Book)request.getAttribute("deletedBook");
			%>
					
	        		<h2><%=book.getName() %> ID: <%= book.getId() %>has been deleted.</h2>	
      		<% } else { %>
    	    <table class="table table-striped">                   
                <tr>
                    <th>
                        Id
                    </th>
                    <th>
                        Name
                    </th> 
                    <th>
                        Price
                    </th>
                    <th>
                        Term
                    </th>
                </tr>
                <tr>
                    <td><%= book.getId() %></td>
                    <td><%= book.getName() %></td>                               
                    <td><%= book.getPrice() %></td>
                    <td><%= book.getTerm() %></td>
                </tr>
            </table>
   		<% } %>
    	 <a href="/books"> books</a>
      
       
    </body>
</html>
