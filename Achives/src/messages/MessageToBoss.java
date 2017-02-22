package messages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbService.DBService;


@WebServlet("/lettertoboss")
public class MessageToBoss extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MessageToBoss() {
        super();
       
    }

		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //Sender sender = new Sender("Sofia.Kareva@aplana.com", "rdfpb,hfrwbz");
		 //sender.send("This is Subject", "SSL: This is text!", "Sofia.Kareva@aplana.com", "sk.jobstuff@gmail.com");
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String achive = request.getParameter("achive");
		
		 
		DBService db = new DBService();
		
		try {
			
			db.addMessagetoBoss(name,achive);
			 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("success", "OK");
		 
		 response.sendRedirect("/Achives/profile.jsp");
		/*PrintWriter out = response.getWriter();
		 out.print("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
	                "http://www.w3.org/TR/html4/loose.dtd\">\n" +
	            "<html> \n" +
	              "<head> \n" +
	                "<meta http-equiv=\"Content-Type\" content=\"text/html; " +
	                  "charset=ISO-8859-1\"> \n" +
	                "<title> Упс!  </title> \n" +
	              "</head> \n" +
	              "<body> <div align='center'> \n" +
	                "<style= \"font-size=\"12px\" color='black'\"" + "\">" +
	                  "Что-то пошло не так" +
	              "</font></body> \n" +
	            "</html>" 
	          );   */
	}

}
