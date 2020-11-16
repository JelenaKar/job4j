package ru.job4j.autosale.servlets;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.autosale.entities.*;
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
import java.util.Map;

public class AdUpdateServlet extends HttpServlet {
    private final SessionFactory sf = new Configuration().configure("postgresql.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sc = req.getSession();
        Seller current = (Seller) sc.getAttribute("current");
        int adNum = Integer.parseInt(req.getParameter("ad"));
        Ad ad = dao.findById(adNum, Ad.class, sf);
        int byId = ad.getSeller().getId();
        if (byId != current.getId()) {
            resp.sendError(403);
            return;
        }
        List<Make> makes = dao.findAll(Make.class, sf);
        List<Model> models = dao.findByConditions(Model.class, sf, Map.of("make_id", ad.getAuto().getMake().getId()));
        List<BodyStyle> bodyStyles = dao.findAll(BodyStyle.class, sf);
        List<EngineType> engineTypes = dao.findAll(EngineType.class, sf);
        List<DriveUnit> driveUnits = dao.findAll(DriveUnit.class, sf);
        List<Transmission> transmissions = dao.findAll(Transmission.class, sf);

        req.setAttribute("title", "Редактирование объявления");
        req.setAttribute("ad", ad);
        req.setAttribute("makes", makes);
        req.setAttribute("models", models);
        req.setAttribute("bodyStyles", bodyStyles);
        req.setAttribute("engineTypes", engineTypes);
        req.setAttribute("driveUnits", driveUnits);
        req.setAttribute("transmissions", transmissions);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/add_update.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession sc = req.getSession();
        Seller current = (Seller) sc.getAttribute("current");
        int adNum = Integer.parseInt(req.getParameter("ad"));
        Ad ad = dao.findById(adNum, Ad.class, sf);
        int byId = ad.getSeller().getId();
        if (byId != current.getId()) {
            resp.sendError(403);
            return;
        }
        int makeId = Integer.parseInt(req.getParameter("make_select"));
        int modelId = Integer.parseInt(req.getParameter("model_select"));
        int manufactured = Integer.parseInt(req.getParameter("manufactured"));
        int bodystyleId = Integer.parseInt(req.getParameter("body_style"));
        int doors = Integer.parseInt(req.getParameter("doors"));
        int engineId = Integer.parseInt(req.getParameter("engine_type"));
        int driveunitId = Integer.parseInt(req.getParameter("drive_unit"));
        int transmissionId = Integer.parseInt(req.getParameter("transmission"));
        String modification = req.getParameter("modification");
        boolean isLeftWheel = Boolean.parseBoolean(req.getParameter("wheel"));
        int mileage = Integer.parseInt(req.getParameter("mileage"));
        boolean isBroken = Boolean.parseBoolean(req.getParameter("is_broken"));
        String color = req.getParameter("color");
        int ownersNumber = Integer.parseInt(req.getParameter("owners"));
        int price = Integer.parseInt(req.getParameter("price"));

        Auto updated = Auto.of(Make.of(makeId), Model.of(modelId), manufactured,
                BodyStyle.of(bodystyleId), doors, EngineType.of(engineId),
                DriveUnit.of(driveunitId), Transmission.of(transmissionId),
                modification, isLeftWheel, mileage, isBroken, color, ownersNumber);
        updated.setId(ad.getAuto().getId());
        ad.setAuto(updated);
        ad.setPrice(price);
        dao.update(ad, sf);
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            writer.print(req.getContextPath() + "/all");
            writer.flush();
        }
    }
}
