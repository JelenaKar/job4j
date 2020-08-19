package ru.job4j.todolist;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ToDoServlet extends HttpServlet {
    private final SessionFactory sf = new Configuration().configure("postgresql.cfg.xml").buildSessionFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("items", Actions.findAll(sf));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/index.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String operation = req.getParameter("op");
        String res;
        ObjectMapper mapper = new ObjectMapper();
        if ("add".equals(operation)) {
            Item added = Actions.add(new Item(req.getParameter("descr"), req.getParameter("login")), sf);
            res = mapper.writeValueAsString(added);
        } else {
            Item updated = Actions.findById(Integer.valueOf(req.getParameter("id")), sf);
            updated.setDone(Boolean.parseBoolean(req.getParameter("isDone")));
            Actions.update(updated, sf);
            res = mapper.writeValueAsString(updated);
        }
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            writer.println(res);
            writer.flush();
        }
    }
}
