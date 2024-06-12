package org.example.security;

import org.example.Entities.UserEntity;
import org.example.Repos.UserRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepo userRepo;
    private final JwtUtil jwtUtil;


    @Override
    public void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("test1");
        Optional<String> optionalJwt = getTokenFromRequest(request);
        System.out.println("optionalJwt: " + optionalJwt);
        if (optionalJwt.isPresent() && jwtUtil.tokenIsValid(optionalJwt.get())){
            System.out.println("token valid");
            String username = jwtUtil.subjectFromToken(optionalJwt.get());
            UserEntity authToken = userRepo.getByUsername(username);
            System.out.println("test2");
            System.out.println(authToken);
            if (authToken == null){
                System.out.println("test2");
                throw new RuntimeException("");
            }
            System.out.println(authToken.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(authToken, null, authToken.getAuthorities())
            );
        }
        filterChain.doFilter(request, response);
    }

    private Optional<String> getTokenFromRequest(HttpServletRequest request){
        String bearer = request.getHeader("Authorization");
        System.out.println("bearer: " + bearer);
        if (bearer == null) return Optional.empty();
        return bearer.startsWith("Bearer ") ? Optional.of(bearer.substring(7)) : Optional.empty();
    }
}
