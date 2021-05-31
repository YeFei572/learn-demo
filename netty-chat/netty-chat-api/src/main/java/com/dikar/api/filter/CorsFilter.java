package com.dikar.api.filter;

import com.dikar.api.config.CorsConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.filter
 * @Author: yefei
 * @CreateTime: 2021-05-31 17:55
 * @Description:
 */
@WebFilter(urlPatterns = "/*", filterName = "corsFilter")
public class CorsFilter implements Filter {
    @Autowired
    private CorsConfig corsConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader("Access-Control-Allow-Origin", corsConfig.getAllowedOrigins());
        response.setHeader("Access-Control-Allow-Methods", corsConfig.getAllowedMethods());
        response.setHeader("Access-Control-Allow-Headers", corsConfig.getAllowedHeaders());
        if (Objects.equals("true", corsConfig.getAllowedCredentials())) {
            response.setHeader("Access-Control-Allow-Credentials", "true");
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
