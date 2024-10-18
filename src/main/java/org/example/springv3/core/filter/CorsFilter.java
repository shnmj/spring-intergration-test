package org.example.springv3.core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("CORS 필터 작동");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Origin", "*"); // localhost:3030 같은 주소 작성
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, PATCH, GET, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600"); // 캐싱 시간 = 1시간
        response.setHeader("Access-Control-Allow-Headers", // Header Custom
                "Origin, X-Api-Key, X-Requested-With, Content-Type, Accept, Authorization"); // x 붙으면 커스터마이징한 key 값

        // 웹소켓: OPTIONS 메서드에 대한 응답 헤더 설정
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            System.out.println("여기걸림?");
            response.setStatus(HttpServletResponse.SC_OK);
        }else {
            chain.doFilter(req, res);
        }

    }
}