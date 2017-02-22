package messages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.BossDataSet;


@WebServlet("/AchiveApproval")
public class AchiveApproval extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AchiveApproval() {
        super();
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String approval = request.getParameter("approve");
		String name = request.getParameter("employeeName");
		String achive = request.getParameter("achive");
		String idmessage = request.getParameter("idmessage");
		String bossname = request.getParameter("name");
		System.out.println(name);
		String id = (String) request.getParameter("id");
		
		DBService dbService = new DBService();
		long idUser =0;
		try {
			idUser = dbService.getUserbyName(name).getId();
		} catch (DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		switch(approval){
		case "Подтвердить": 
				dbService.addAnswer(idUser, id+","+achive+",Подтверждено");
				dbService.addscore(idUser,achive);
				dbService.deleteUserRequest(idmessage);
				dbService.addRecievedAchives(idUser,achive);
				break;
			
		case "Отказать":dbService.addAnswer(idUser, id+","+achive+",Отказано");
		dbService.deleteUserRequest(idmessage);
		break;
	
		}
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (DBException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		BossDataSet set = null;
		Map<String,String> message = null;
		try {
			set = dbService.getBossProfile(bossname);
			message = dbService.getAchivesonApproval(set.getId());
		} catch (DBException e) {
			
			e.printStackTrace();
		}
		session.removeAttribute("message");
		session.setAttribute("message", message);
		session.removeAttribute("profile");
		session.setAttribute("profile", set);
		response.setHeader("Refresh", "0; URL=/Achives/bossprofile.jsp");
		//request.getRequestDispatcher("/bossprofile.jsp").forward(request, response);
		//response.sendRedirect("/Achives/bossprofile.jsp");
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
