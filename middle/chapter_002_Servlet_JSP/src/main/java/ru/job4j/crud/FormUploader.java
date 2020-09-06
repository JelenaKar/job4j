package ru.job4j.crud;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс-загрузчик формы.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class FormUploader {
    private static final Logger LOG = LogManager.getLogger(FormUploader.class);
    private HttpServletRequest req;
    private ServletContext context;
    private List<FileItem> items;
    private FileItem file;
    private List<FileItem> fields = new ArrayList<>();
    private User user;
    private Actions action;

    public FormUploader(HttpServletRequest req, ServletContext context) {
        this.req = req;
        this.context = context;
    }

    /**
     * Загружает и парсит форму со всем её содержимым.
     * @throws FileUploadException
     * @throws IOException
     */
    public void upload() throws FileUploadException, IOException {
        this.parseForm();
        this.fileUpload();
        this.user = this.userFromForm();
    }

    /**
     * Получает объект пользователя из формы.
     * @return объект пользователя с соответствующими значениями полей.
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Получает объект действия над пользователем.
     * @return действие над пользователем.
     */
    public Actions getAction() {
        return this.action;
    }

    private void fileUpload() throws IOException {
        if (this.file.getSize() != 0) {
            File folder = new File(User.IMG_DIR);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File file = new File(folder + File.separator + this.file.getName());
            try (FileOutputStream out = new FileOutputStream(file)) {
                out.write(this.file.getInputStream().readAllBytes());
            }
        }
    }

    private User userFromForm() {
        String name = null;
        String login = null;
        String email = null;
        String photoid = null;
        if (!this.fields.isEmpty()) {
            for (FileItem field : this.fields) {
                try {
                    if (field.isFormField()) {
                        switch (field.getFieldName()) {
                            case ("name"):
                                name = field.getString("UTF-8");
                                break;
                            case ("login"):
                                login = field.getString("UTF-8");
                                break;
                            case ("email"):
                                email = field.getString("UTF-8");
                                break;
                            case ("photoid"):
                                photoid = "".equals(field.getString("UTF-8")) ? null : field.getString("UTF-8");
                                break;
                            case ("action"):
                                action = Actions.valueOf(field.getString("UTF-8").toUpperCase());
                                break;
                            default:
                                break;
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    LOG.error(e.getMessage());
                }
            }
            if (this.file.getSize() > 0) {
                if (photoid != null) {
                    new File(User.IMG_DIR + File.separator + photoid).delete();
                }
                photoid = this.file.getName();
            }
        }
        return new User(name, login, email, photoid);
    }

    private void parseForm() throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File repository = (File) context.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        this.items = upload.parseRequest(req);
        for (FileItem item : this.items) {
            if (!item.isFormField()) {
                this.file = item;
            } else {
                this.fields.add(item);
            }
        }
    }
}