package utez.edu.mx.Aplicacion.de.principios.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utez.edu.mx.Aplicacion.de.principios.Models.Cliente;
import utez.edu.mx.Aplicacion.de.principios.Repositories.ClienteRepository;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private JwtUtils jwtUtils;

    public String login(String correo, String password) {
        Cliente cliente = clienteRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, cliente.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return jwtUtils.generateToken(correo);
    }
}
