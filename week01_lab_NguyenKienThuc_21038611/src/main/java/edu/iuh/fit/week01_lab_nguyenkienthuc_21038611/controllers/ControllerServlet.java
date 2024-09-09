// ControllerServlet.java
package edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.controllers;

import edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.entities.Account;
import edu.iuh.fit.week01_lab_nguyenkienthuc_21038611.services.AccountServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ControlServlet", value = "/ControlServlet")
public class ControllerServlet extends HttpServlet {
    private AccountServices accountServices;

    @Override
    public void init() throws ServletException {
        accountServices = new AccountServices();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if ("login".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Account account = accountServices.validateLogin(username, password);

            if (account != null) {
                session.setAttribute("username", username);
                session.setAttribute("account", account);

                session.setAttribute("accounts", accountServices.getAllAccounts());
                response.sendRedirect("dashboard.jsp");
            } else {
                request.setAttribute("errorMessage", "Invalid username or password");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else if ("add".equals(action)) {
            request.getRequestDispatcher("addAndEdit.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            String accountId = request.getParameter("accountId");
            Account account = accountServices.findAccountByAccountId(accountId);
            request.setAttribute("account", account);
            request.getRequestDispatcher("addAndEdit.jsp").forward(request, response);
            if (account != null) {
                request.setAttribute("account", account);
                request.getRequestDispatcher("addAndEdit.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Account not found.");
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
            }
        } else if ("saveAdd".equals(action)) {
            String accountId = request.getParameter("accountId");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String statusStr = request.getParameter("status");
            String[] roles = request.getParameterValues("roles");

            if (accountId == null || fullName == null || password == null || email == null || phone == null || statusStr == null) {
                request.setAttribute("errorMessage", "All fields are required.");
                request.getRequestDispatcher("addAndEdit.jsp").forward(request, response);
                return;
            }

            try {
                byte status = Byte.parseByte(statusStr);

                Account account = new Account();
                account.setAccountId(accountId);
                account.setFullName(fullName);
                account.setPassword(password);
                account.setEmail(email);
                account.setPhone(phone);
                account.setStatus(status);


                accountServices.addAccount(account); // Save account to the database
                session.setAttribute("accounts", accountServices.getAllAccounts());
                response.sendRedirect("ControlServlet?action=adminDashboard");
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid status value.");
                request.getRequestDispatcher("addAndEdit.jsp").forward(request, response);
            }
        } else if ("saveEdit".equals(action)) {
            String accountId = request.getParameter("accountId");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String statusStr = request.getParameter("status");
            String[] roles = request.getParameterValues("roles");

            if (accountId == null || fullName == null || password == null || email == null || phone == null || statusStr == null) {
                request.setAttribute("errorMessage", "All fields are required.");
                request.getRequestDispatcher("addAndEdit.jsp").forward(request, response);
                return;
            }

            try {
                byte status = Byte.parseByte(statusStr);

                accountServices.updateAccount(accountId, fullName, password, email, phone, status, Arrays.asList(roles));

                List<Account> accounts = accountServices.getAllAccounts();
                session.setAttribute("accounts", accounts);
                response.sendRedirect("dashboard.jsp");
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid status value.");
                request.getRequestDispatcher("addAndEdit.jsp").forward(request, response);
            }
        } else if ("delete".equals(action)) {
            String accountId = request.getParameter("accountId");
            accountServices.deleteAccountById(accountId);
            session.setAttribute("accounts", accountServices.getAllAccounts());
            response.sendRedirect("ControlServlet?action=adminDashboard");
        } else {
            doGet(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action");

        if (action == null) {
            action = "login";
        }

        if (session != null && session.getAttribute("username") != null) {
            switch (action) {
                case "adminDashboard":
                    List<Account> accounts = accountServices.getAllAccounts();
                    request.setAttribute("accounts", accounts);
                    request.getRequestDispatcher("dashboard.jsp").forward(request, response);
                    break;
                default:
                    response.getWriter().write("Invalid action");
            }
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}