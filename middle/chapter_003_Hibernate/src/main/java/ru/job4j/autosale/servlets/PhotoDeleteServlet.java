package ru.job4j.autosale.servlets;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.autosale.model.Actions;
import ru.job4j.autosale.entities.Photo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.job4j.autosale.entities.Photo.SYSTEM_PATH;

public class PhotoDeleteServlet extends HttpServlet {
    private final SessionFactory sf = new Configuration().configure("postgresql.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int photoId = Integer.parseInt(req.getParameter("id"));
        Photo photo = dao.findById(photoId, Photo.class, sf);
        boolean deleted = Files.deleteIfExists(Paths.get(SYSTEM_PATH + File.separator + photo.getFolder().getName() + File.separator + photo.getName()));
        if (deleted) {
            dao.delete(photo, sf);
        }
    }
}
