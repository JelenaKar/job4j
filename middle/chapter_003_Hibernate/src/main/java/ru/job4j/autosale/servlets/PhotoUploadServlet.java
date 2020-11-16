package ru.job4j.autosale.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.autosale.model.Actions;
import ru.job4j.autosale.entities.Ad;
import ru.job4j.autosale.entities.Folder;
import ru.job4j.autosale.entities.Photo;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static ru.job4j.autosale.entities.Photo.SYSTEM_PATH;

public class PhotoUploadServlet extends HttpServlet {
    private final SessionFactory sf = new Configuration().configure("postgresql.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        System.out.println(req.getParameter("folder"));
        try {
            List<FileItem> items = upload.parseRequest(req);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    Ad ad = ("".equals(req.getParameter("ad"))) ? null : dao.findById(Integer.parseInt(req.getParameter("ad")), Ad.class, sf);
                    Folder folder;
                    if ("undefined".equals(req.getParameter("folder"))) {
                        folder = Folder.of(RandomStringUtils.randomAlphanumeric(10), ad);
                    } else {
                        folder = dao.findByField(Folder.class, sf, "name", req.getParameter("folder")).get(0);
                    }
                    Path path = Files.createDirectories(Path.of(SYSTEM_PATH + File.separator + folder.getName()));
                    String filename = RandomStringUtils.randomAlphanumeric(10) + "." + this.getExtensionByStringHandling(item.getName());
                    File file = new File(path + File.separator + filename);
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                    Photo added = dao.add(Photo.of(filename, folder), sf);
                    ObjectMapper mapper = new ObjectMapper();
                    try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
                        writer.println(mapper.writeValueAsString(added));
                        writer.flush();
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    public String getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1))
                .map(Object::toString)
                .orElse("");
    }
}
