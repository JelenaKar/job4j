package ru.job4j.crud;

import org.apache.commons.fileupload.FileUploadException;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "javax.management.*", "org.w3c.*"})
public class UsersCreateServletTest {

    Validate validate = new ValidateStub();

    public void testUserCreateEdit(Class<? extends UserBaseServlet> servletClass, Consumer<HttpServletRequest> whenPart, Supplier<Void> assertion) throws ServletException, IOException, FileUploadException {
        UserBaseServlet servlet = PowerMock.createPartialMock(servletClass, "getServletConfig");
        ServletConfig servletConfig = PowerMock.createPartialMock(ServletConfig.class, "getServletContext");
        ServletContext servletContext = PowerMock.createNiceMock(ServletContext.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);

        servlet.getServletConfig();
        PowerMock.expectLastCall().andReturn(servletConfig);
        servletConfig.getServletContext();
        PowerMock.expectLastCall().andReturn(servletContext);

        whenPart.accept(req);

        PowerMock.replay(servlet, servletConfig, servletContext);
        servlet.doPost(req, resp);

        assertion.get();
    }

    @Test
    public void whenAddUserThenStoreIt() throws ServletException, IOException, FileUploadException {
        Consumer<HttpServletRequest> when = req -> {
            when(req.getParameter("name")).thenReturn("Иван");
            when(req.getParameter("login")).thenReturn("ivan");
            when(req.getParameter("email")).thenReturn("ivan@test.ru");
        };

        Supplier<Void> assertPart = () -> {
            assertEquals(validate.findAll().iterator().next().getName(), "Иван");
            assertEquals(validate.findAll().iterator().next().getLogin(), "ivan");
            assertEquals(validate.findAll().iterator().next().getEmail(), "ivan@test.ru");
            return null;
        };
        this.testUserCreateEdit(UserCreateServlet.class, when, assertPart);
    }

    @Test
    public void whenEditUserThenChangeUserData() throws ServletException, IOException, FileUploadException {
        validate.add(new User("Test User", "test", "test@test.ru", null));
        assertEquals(validate.findAll().iterator().next().getName(), "Test User");

        Consumer<HttpServletRequest> when = req -> {
            when(req.getParameter("name")).thenReturn("Иван");
            when(req.getParameter("login")).thenReturn("ivan");
            when(req.getParameter("email")).thenReturn("ivan@test.ru");
        };

        Supplier<Void> assertPart = () -> {
            assertEquals(validate.findAll().iterator().next().getName(), "Иван");
            assertEquals(validate.findAll().iterator().next().getLogin(), "ivan");
            assertEquals(validate.findAll().iterator().next().getEmail(), "ivan@test.ru");
            return null;
        };
        this.testUserCreateEdit(UserCreateServlet.class, when, assertPart);
    }

    @Test
    public void whenDeleteUserThenStoreIsEmpty() throws ServletException, IOException, FileUploadException {
        validate.add(new User("Test User", "test", "test@test.ru", null));
        assertThat(validate.findAll().size(), Is.is(1));

        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpServletRequest req = PowerMock.createNiceMock(HttpServletRequest.class);
        RequestDispatcher dispatcher = PowerMock.createNiceMock(RequestDispatcher.class);
        req.getRequestDispatcher("WEB-INF/views/list.jsp");
        PowerMock.expectLastCall().andReturn(dispatcher);

        req.getParameter("id");
        PowerMock.expectLastCall().andReturn("1");

        PowerMock.replay(req, dispatcher);
        new UsersServlet().doPost(req, resp);
        assertThat(validate.findAll().size(), Is.is(0));
    }

}