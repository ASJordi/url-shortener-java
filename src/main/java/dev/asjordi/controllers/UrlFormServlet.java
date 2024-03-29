package dev.asjordi.controllers;

import dev.asjordi.models.Url;
import dev.asjordi.service.IService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/urls/shorten")
public class UrlFormServlet extends HttpServlet {

    @Inject
    IService<Url> service;

    private static final String AUTH_CODE = "1234";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();

        String link = req.getParameter("link");
        String hash = req.getParameter("hash");
        String auth = req.getParameter("auth");

        if (link == null || link.trim().isBlank()) errors.put("link", "Link is required");
        if (hash == null || hash.trim().isBlank()) errors.put("hash", "Hash is required");
        if (auth == null || auth.trim().isBlank()) errors.put("auth", "Auth code is required");
        if (!auth.equals(AUTH_CODE)) errors.put("auth", "Invalid auth code");

        Url url = new Url();
        url.setUrl(link);
        url.setHash(hash);
        url.setCreated(LocalDate.now());

        if (errors.isEmpty()) {
            service.save(url);
            resp.sendRedirect(req.getContextPath() + "/index");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("url", url);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
