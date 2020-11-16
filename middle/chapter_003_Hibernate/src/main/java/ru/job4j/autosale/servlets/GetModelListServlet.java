package ru.job4j.autosale.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.autosale.model.Actions;
import ru.job4j.autosale.entities.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class GetModelListServlet extends HttpServlet {
    private final SessionFactory sf = new Configuration().configure("postgresql.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int makeSelect = Integer.parseInt(req.getParameter("make_id"));
        ObjectMapper mapper = new ObjectMapper();
        List<Model> models = dao.findByConditions(Model.class, sf, Map.of("make_id", makeSelect));
        String res = mapper.writeValueAsString(models);
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            writer.println(res);
            writer.flush();
        }
    }
}
