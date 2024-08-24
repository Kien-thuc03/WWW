package edu.iuh.fit.session_01;

import edu.iuh.fit.session_01.beans.Login;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Login loginBean = new Login();
        boolean isValidUser = loginBean.login(username, password);

        if (isValidUser) {
            out.println("<html><body>");
            out.println("<h1>Welcome, " + username + "!</h1>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h1>Login Failed</h1>");
            out.println("</body></html>");
        }
    }
}