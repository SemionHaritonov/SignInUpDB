package org.example;

import accounts.AccountService;
import dbService.DBService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;

public class Main {
    public static void main(String[] args) {
        DBService dbService = new DBService();
        dbService.printConnectInfo();

        AccountService accountService = new AccountService(dbService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");

        Server server = new Server(8080);
        server.setHandler(context);

        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        java.util.logging.Logger.getGlobal().info("Server started");
        try {
            server.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}