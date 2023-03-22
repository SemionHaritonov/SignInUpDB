package main.servlets;

import main.DBException;
import main.accounts.AccountService;
import main.accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("Bad request");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = null;
        try {
            profile = accountService.getUserByLogin(login);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }

        try {
            if (accountService.getUserByLogin(login) == null) {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print("Unauthorized");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } catch (DBException e) {
            throw new RuntimeException(e);
        }

        if (!profile.getPass().equals(password)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Authorized: " + login);
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
    }
}
