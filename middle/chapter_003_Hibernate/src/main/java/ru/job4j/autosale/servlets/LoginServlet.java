package ru.job4j.autosale.servlets;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.autosale.entities.Seller;
import ru.job4j.autosale.model.Actions;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private final SessionFactory sf = new Configuration().configure("postgresql.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        List<Seller> current = dao.findByField(Seller.class, sf, "email", email);
        if (current.size() != 0 && current.get(0).getPassword().equals(password)) {
            HttpSession sc = req.getSession();
            sc.setAttribute("current", current.get(0));
        } else {
            resp.setStatus(403);
            try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
                writer.println("Неверный email или пароль");
                writer.flush();
            }
            /*req.setAttribute("error", "Неверный email или пароль");
            req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);*/
        }
    }
}
