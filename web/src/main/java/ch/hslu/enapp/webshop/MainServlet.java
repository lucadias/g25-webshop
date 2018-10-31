package ch.hslu.enapp.webshop;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import ch.hslu.enapp.webshop.services.ProductServicesBeanLocal;
import org.apache.logging.log4j.LogManager;

@WebServlet(name = "MainServlet" ,urlPatterns = {"/Shop"})
public class MainServlet extends HttpServlet {

    @Inject
    private ProductsBeanLocal products;

    @Inject
    private ProductServicesBeanLocal producServices;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE HTML>");
            out.println("<HTML>");
            out.println("<HEAD>");
            out.println("<TITLE>Hello World</TITLE>");
            out.println("</HEAD>");
            out.println("<BODY>");
            out.println("<H1>HELLO WORLD</H1>");
            out.println("<H2>" + producServices.getFirstProduct().getName() + "</H2>");
            out.println("<H2>asdf</H2>");
            out.println("</BODY>");
            out.println("</HTML>");;
            out.flush();
        } catch (Exception e) {
            LogManager.getLogger(MainServlet.class).error("exception", e);
        }


    }
}
