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

@WebServlet(urlPatterns = "/r/*")
public class UrlRedirectServlet extends HttpServlet {

    @Inject
    IService<Url> service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo != null && !pathInfo.isBlank()) {
            String hash = pathInfo.substring(1);
            System.out.println("Hash: " + hash);
            var oUrl = service.findByHash(hash);

            if (oUrl.isPresent()) resp.sendRedirect(oUrl.get().getUrl());
            else resp.sendError(HttpServletResponse.SC_NOT_FOUND, "URL not found");
        }
    }
}
