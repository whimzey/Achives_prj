import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {    
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/logon";
        String registerURI = request.getContextPath() + "/registration.jsp";
        String registeringURI = request.getContextPath() + "/registration";

        boolean loggedIn = session != null && session.getAttribute("profile") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);

        if(request.getRequestURI().equals(registerURI)||request.getRequestURI().equals(registeringURI)){
        	chain.doFilter(request, response);
        	
        } else if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } 
         
        	else {
            response.sendRedirect(loginURI);
        }
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

    // ...
}
