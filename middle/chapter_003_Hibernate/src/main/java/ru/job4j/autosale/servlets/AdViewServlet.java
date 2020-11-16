package ru.job4j.autosale.servlets;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.autosale.entities.Ad;
import ru.job4j.autosale.entities.Seller;
import ru.job4j.todolist.Actions;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdViewServlet extends HttpServlet {
    private final SessionFactory sf = new Configuration().configure("postgresql.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int adNum = Integer.parseInt(req.getParameter("ad"));
        req.setAttribute("ad", dao.findById(adNum, Ad.class, sf));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/view.jsp");
        requestDispatcher.forward(req, resp);
    }
}
