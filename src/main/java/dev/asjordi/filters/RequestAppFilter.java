package dev.asjordi.filters;

import dev.asjordi.models.Url;
import dev.asjordi.service.IService;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebFilter("/*")
public class RequestAppFilter implements Filter {

    @Inject
    IService<Url> service;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        List<String> hashes = service.findAll().stream().map(Url::getHash).collect(Collectors.toList());
        servletRequest.setAttribute("hashes", hashes);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
