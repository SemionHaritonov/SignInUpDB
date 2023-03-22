package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import dbService.DBException;
import dbService.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {



        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Empty fields");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            if (accountService.getUserByLogin(login) != null) {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println(login + " is registered already");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        } catch (DBException e) {
            throw new RuntimeException(e);
        }

        UserProfile userProfile = new UserProfile(login, password);
        try {
            accountService.addNewUser(userProfile);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Registered: " + login);
        response.setStatus(HttpServletResponse.SC_OK);


    }
}
