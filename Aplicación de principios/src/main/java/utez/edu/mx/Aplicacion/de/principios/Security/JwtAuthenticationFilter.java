package utez.edu.mx.Aplicacion.de.principios.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import utez.edu.mx.Aplicacion.de.principios.Models.Cliente;
import utez.edu.mx.Aplicacion.de.principios.Repositories.ClienteRepository;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (jwtUtils.isTokenValid(token)) {
                String correo = jwtUtils.extractCorreo(token);

                if (correo != null) {
                    Cliente cliente = clienteRepository.findByCorreo(correo).orElse(null);

                    if (cliente != null) {
                        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                                cliente, null, List.of()
                        );
                        SecurityContextHolder.getContext().setAuthentication(auth);
                        System.out.println("Cliente autenticado: " + cliente.getCorreo());
                    } else {
                        System.out.println("Cliente no encontrado en la base de datos");
                    }
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/api/auth") || path.startsWith("/error");
    }
}
