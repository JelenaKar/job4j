package ru.job4j.autosale.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CRUDFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest sreq, ServletResponse sresp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sreq;
        HttpServletResponse resp = (HttpServletResponse) sresp;
        if (req.getSession().getAttribute("current") == null) {
            resp.sendRedirect(req.getContextPath() + "/in");
            return;
        }
        chain.doFilter(sreq, sresp);
    }

    @Override
    public void destroy() {

    }
}
