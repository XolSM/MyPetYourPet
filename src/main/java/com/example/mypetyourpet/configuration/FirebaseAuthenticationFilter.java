package com.example.mypetyourpet.configuration;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

    public class FirebaseAuthenticationFilter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(
                HttpServletRequest request,
                HttpServletResponse response,
                FilterChain filterChain
        ) throws ServletException, IOException {

            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String idToken = authHeader.substring(7);

                try {
                    FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
                    String uid = decodedToken.getUid();

                    // You can map roles later using custom claims. For now, just give a USER role.
                    var authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

                    var authentication =
                            new UsernamePasswordAuthenticationToken(uid, null, authorities);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (Exception e) {
                    // Invalid token
                    System.out.println("❌ Firebase token invalid: " + e.getMessage());
                    SecurityContextHolder.clearContext();
                    // Optionally stop here:
                    // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Firebase token");
                    // return;
                }
            }

            filterChain.doFilter(request, response);
        }
    }


