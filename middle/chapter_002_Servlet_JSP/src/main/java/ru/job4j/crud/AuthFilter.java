package ru.job4j.crud;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class AuthFilter implements Filter {
    private List<Function<String, Boolean>> filters = List.of(
            uri -> uri.endsWith("/login"),
            uri -> uri.endsWith("/hall"),
            uri -> uri.contains(".html"),
            uri -> uri.contains(".css"),
            uri -> uri.contains("/greet")
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest sreq, ServletResponse sresp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sreq;
        HttpServletResponse resp = (HttpServletResponse) sresp;
        String uri = req.getRequestURI();
        if (this.filter(uri)) {
            chain.doFilter(sreq, sresp);
            return;
        }
        if (req.getSession().getAttribute("current") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        chain.doFilter(sreq, sresp);
    }

    @Override
    public void destroy() {

    }

    private boolean filter(String uri) {
        boolean res = false;
        for (Function<String, Boolean> filter : this.filters) {
            res |= filter.apply(uri);
        }
        return res;
    }
}
