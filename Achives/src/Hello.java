

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

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
import dbService.dataSets.BossDataSet;
import dbService.dataSets.UsersDataSet;


@WebServlet("/logon")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Hello() {
        super();
        
    }
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	String login;
    	String pass;
    	String isaboss;
    	
    	/*if(request.getAttribute("redirect")!=null){
    		login =	(String) request.getAttribute("username");
    		pass = (String) request.getAttribute("password");
    		isaboss = (String) request.getAttribute("isaboss");
    	}
    	else{*/
    		 login = request.getParameter("username");
    		 isaboss = request.getParameter("isaboss");
    		 pass = request.getParameter("password");
    	//}

response.setContentType("text/html;charset=utf-8");
response.setStatus(HttpServletResponse.SC_OK);

PrintWriter out = response.getWriter();
DBService dbService = new DBService();

if(isaboss!=null){
	
	BossDataSet set = null;
	Map<String,String> message = null;
	ArrayList<String> achives = null;
	
	
	try {
		set = dbService.getBossProfile(login);
		message = dbService.getAchivesonApproval(set.getId());
		achives = dbService.getAchivesList(set.getId());
		
	} catch (DBException e) {
		
		e.printStackTrace();
	}
	HttpSession session = request.getSession(); 
	session.setAttribute("profile", set);
	session.setAttribute("message", message);
	session.setAttribute("achives", achives);
	
	request.getRequestDispatcher("/bossprofile.jsp").forward(request, response);
}else {
	
	UsersDataSet dataSet = null;
	ArrayList<String> answerfromboss = null;
	try {
		
		dataSet = dbService.getUser(login);
		answerfromboss = dbService.getAnswerfromBoss(dataSet.getId());
	} catch (DBException e) {
		
		e.printStackTrace();
	}
if(dataSet!=null){
		
HttpSession session = request.getSession(); 
session.setAttribute("profile", dataSet);
session.setAttribute("answerfromboss", answerfromboss);
	request.getRequestDispatcher("/profile.jsp").forward(request, response);
	
}
else {
	out.print(
	        "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
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
	          );   
}}
}}


