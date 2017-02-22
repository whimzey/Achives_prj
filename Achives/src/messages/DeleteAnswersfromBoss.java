package messages;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;


@WebServlet("/deleteanswerfromboss")
public class DeleteAnswersfromBoss extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeleteAnswersfromBoss() {
        super();
       
    }
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		DBService db = new DBService();
		ArrayList<String> answerfromboss = null;
		try {
			db.deleteAnswersfromBoss(id);
			//request.setAttribute("profile", db.getUser(Long.parseLong(id)));
			//request.setAttribute("answerfromboss", db.getAnswerfromBoss(Long.parseLong(id)));
			//UsersDataSet data = db.getUser(Long.parseLong(id));
			/*request.setAttribute("username", data.getUserName());
			request.setAttribute("password", data.getPassword());
			request.setAttribute("redirect", "true");*/
			answerfromboss = db.getAnswerfromBoss(Long.parseLong(id));
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession(); 
		session.removeAttribute("answerfromboss");
		session.setAttribute("answerfromboss", answerfromboss);
		response.setHeader("Refresh", "0; URL=/Achives/profile.jsp");
		
	}

}
