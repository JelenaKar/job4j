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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private final SessionFactory sf = new Configuration().configure("postgresql.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sc = req.getSession();
        //sc.setAttribute("current", dao.findById(1, Seller.class, sf));
        req.setAttribute("ads", dao.findAll(Ad.class, sf));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
