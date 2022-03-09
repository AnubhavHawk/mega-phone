package com.bbps.gateway.rest.filters;

import com.bbps.gateway.rest.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {
    @Autowired
    private JwtUtil jwtUtil;
    JwtAuthenticationFilter() {
        super(Config.class);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    @Override
    public GatewayFilter apply(JwtAuthenticationFilter.Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            // Return unauthorised if authorization header is not present.
            if(!request.getHeaders().containsKey("Authorization")) {
//                return;
                return this.onError(exchange, "No Authorization header present", HttpStatus.UNAUTHORIZED);
            }
            String authorizationHeader = request.getHeaders().get("Authorization").get(0);
            String token = authorizationHeader.substring(7);
            System.out.println(token);
            try {
                boolean isTokenValid = jwtUtil.validateToken(token);
            } catch (ExpiredJwtException | MalformedJwtException | SignatureException e) {
//                return ;
                return this.onError(exchange, "Invalid Token", HttpStatus.UNAUTHORIZED);
            }
            catch (Exception e) {
                return this.onError(exchange, "No Authorization header present", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        };
    }
    public static class Config { }
}
