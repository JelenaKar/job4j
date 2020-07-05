package ru.job4j.cinema;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PaymentServlet extends HttpServlet {
    Storage db = DataBase.getInstance();
    private static final int TEST_HALL_ID = 1;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sc = req.getSession();
        sc.setAttribute("chosen", db.getPlace(
                Integer.parseInt(req.getParameter("hall")),
                Integer.parseInt(req.getParameter("row")),
                Integer.parseInt(req.getParameter("col")))
        );
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/payment.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession sc = req.getSession();
        Place chosen = (Place) sc.getAttribute("chosen");
        if (db.doPayment(TEST_HALL_ID, chosen.getRow(), chosen.getCol(), req.getParameter("fullname"), req.getParameter("phone"))) {
            req.setAttribute("success", "Успешное завершение покупки");
        } else {
            req.setAttribute("success", "Ошибка при совершении покупки");
            req.setAttribute("error", true);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/completion.jsp");
        requestDispatcher.forward(req, resp);
    }
}