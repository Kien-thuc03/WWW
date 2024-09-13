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

        switch (action) {
            case "login":
                handleLogin(request, response, session);
                break;
            case "assignRoles":
                handleAssignRoles(request, response, session);
                break;
            case "add":
                request.getRequestDispatcher("addAndEdit.jsp").forward(request, response);
                break;
            case "edit":
                handleEdit(request, response);
                break;
            case "saveAdd":
            case "saveEdit":
                handleSave(request, response, session);
                break;
            case "delete":
                handleDelete(request, response, session);
                break;
            default:
                doGet(request, response);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
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
    }

    private void handleAssignRoles(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        List<Account> accounts = accountServices.getAllAccounts(); // Retrieve all accounts
        boolean hasError = false; // To track if any errors occurred
        String errorMessage = ""; // Error message holder

        if (accounts != null) {
            for (Account account : accounts) {
                String accountId = request.getParameter("accountId_" + account.getAccountId());
                String[] roles = request.getParameterValues("roles_" + account.getAccountId());

                // Check if no roles are selected for this account
                if (roles == null || roles.length == 0) {
                    hasError = true;
                    errorMessage = "Please assign at least one role for each account.";
                    break; // Stop further processing since we found an error
                }

                if (accountId != null && roles != null) {
                    List<String> roleNames = Arrays.asList(roles);
                    accountServices.updateRoles(accountId, roleNames); // Update roles for this account
                }
            }

            if (hasError) {
                // Set error message in request and forward back to dashboard
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
            } else {
                // Update session and redirect if no errors
                session.setAttribute("accounts", accountServices.getAllAccounts());
                response.sendRedirect("dashboard.jsp");
            }
        } else {
            // Handle case where no accounts are available
            setErrorAndForward(request, response, "No accounts available for role assignment.", "dashboard.jsp");
        }
    }


    private void handleEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountId = request.getParameter("accountId");
        Account account = accountServices.findAccountByAccountId(accountId);

        if (account != null) {
            request.setAttribute("account", account);
            request.getRequestDispatcher("addAndEdit.jsp").forward(request, response);
        } else {
            setErrorAndForward(request, response, "Account not found.", "dashboard.jsp");
        }
    }

    private void handleSave(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        String accountId = request.getParameter("accountId");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String statusStr = request.getParameter("status");
        String[] roles = request.getParameterValues("roles");

        if (accountId == null || fullName == null || password == null || email == null || phone == null || statusStr == null || roles == null || roles.length == 0) {
            setErrorAndForward(request, response, "All fields and at least one role are required.", "addAndEdit.jsp");
            return;
        }

        try {
            byte status = Byte.parseByte(statusStr);
            List<String> roleNames = Arrays.asList(roles);
            accountServices.updateAccount(accountId, fullName, password, email, phone, status, roleNames);
            session.setAttribute("accounts", accountServices.getAllAccounts());
            response.sendRedirect("dashboard.jsp");
        } catch (NumberFormatException e) {
            setErrorAndForward(request, response, "Invalid status value.", "addAndEdit.jsp");
        }
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        String accountId = request.getParameter("accountId");
        accountServices.deleteAccountById(accountId);
        session.setAttribute("accounts", accountServices.getAllAccounts());
        response.sendRedirect("ControlServlet?action=adminDashboard");
    }

    private void setErrorAndForward(HttpServletRequest request, HttpServletResponse response, String errorMessage, String page) throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher(page).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action");

        if (action == null) {
            action = "login";
        }

        if (session != null && session.getAttribute("username") != null) {
            if ("adminDashboard".equals(action)) {
                List<Account> accounts = accountServices.getAllAccounts();
                request.setAttribute("accounts", accounts);
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
            } else {
                response.getWriter().write("Invalid action");
            }
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
