package ru.job4j.cinema;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class HallServlet extends HttpServlet {
    private Storage db = DataBase.getInstance();
    private static final int TEST_HALL_ID = 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hall hall = new Hall(db.getAllPlaces(TEST_HALL_ID));
        req.setAttribute("hall", hall);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/index.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        List<Place> hall = db.getAllPlaces(TEST_HALL_ID);
        ArrayList<String> places = hall.stream().filter(p -> !p.isFree())
                .collect(ArrayList::new,
                        (list, item)->list.add("r" + item.getRow() + "c" + item.getCol()),
                        ArrayList::addAll);
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            writer.println(Helpers.listToJavascript(places));
            writer.flush();
        }
    }
}
