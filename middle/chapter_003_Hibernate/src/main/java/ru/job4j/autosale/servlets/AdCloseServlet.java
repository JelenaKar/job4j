package ru.job4j.autosale.servlets;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.autosale.model.Actions;
import ru.job4j.autosale.entities.Ad;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdCloseServlet extends HttpServlet {
    private final SessionFactory sf = new Configuration().configure("postgresql.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int adId = Integer.parseInt(req.getParameter("id"));
        Ad closing = dao.findById(adId, Ad.class, sf);
        closing.setSold(true);
        dao.update(closing, sf);
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            writer.print(req.getContextPath() + "/all");
            writer.flush();
        }
    }
}
