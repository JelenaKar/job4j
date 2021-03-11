package ru.job4j.autosale.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.autosale.model.Actions;
import ru.job4j.autosale.model.FilterDispatcher;
import ru.job4j.autosale.model.Filters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class AdFilterServlet extends HttpServlet {
    private final SessionFactory sf = new Configuration().configure("postgresql.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();
    private final FilterDispatcher dispatcher = FilterDispatcher.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String[]> params = new HashMap<>(req.getParameterMap());
        String filter = params.remove("filter")[0];
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            writer.println(mapper.writeValueAsString(dispatcher.execute(Filters.fromString(filter), params)));
            writer.flush();
        }
    }
}
