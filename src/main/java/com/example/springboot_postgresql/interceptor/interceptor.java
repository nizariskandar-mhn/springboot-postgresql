package com.example.springboot_postgresql.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class interceptor implements HandlerInterceptor {
    

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Pre Handle method is Calling");

        // Retrieve token from request
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("Unauthorized request: Missing or invalid token");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Missing or invalid token");
            return false; // Reject request
        }

        // Extract actual token (remove "Bearer " from the header value)
        String token = authHeader.substring(7);
        System.out.println("Token received: " + token);

        return true; // Allow request to proceed
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        System.out.println("Post Handle method is Calling");

        // Add any post-processing logic here if necessary, like modifying the response
        String responseMessage = "Response processed successfully";
        response.setHeader("X-Response-Message", responseMessage);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        System.out.println("Request and Response is completed");

        // Add any cleanup logic, e.g., logging request completion
        if (exception != null) {
            System.out.println("Exception occurred: " + exception.getMessage());
        }
    }
}
