package com.example.projeto_lume.controller;

import com.example.projeto_lume.dto.UsuarioDTO;
import com.example.projeto_lume.service.UsuarioService;
import com.example.projeto_lume.token.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints para autenticação e gerenciamento de tokens JWT")
public class AuthController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UsuarioService usuarioService;

    public AuthController(JwtTokenProvider jwtTokenProvider, UsuarioService usuarioService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    @Operation(summary = "Realizar login", description = "Autentica o usuário e retorna tokens JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas",
                    content = @Content)
    })
    public ResponseEntity<?> login(@RequestBody UsuarioDTO request) {

        if(usuarioService.autenticar(request.getEmail(),request.getSenha())){
            Map<String, Object> response = new HashMap<>();
            response.put("token", jwtTokenProvider.generateToken(request.getEmail()));
            response.put("refreshToken", jwtTokenProvider.generateRefreshToken(request.getEmail()));
            response.put("user", request.getEmail());
            return ResponseEntity.ok(response);
        }

        throw new RuntimeException("Credenciais inválidas");
    }

    @PostMapping("/refresh")
    @Operation(summary = "Renovar token", description = "Gera um novo token de acesso usando o refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token renovado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Refresh token inválido ou expirado",
                    content = @Content)
    })
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        if (jwtTokenProvider.validateToken(refreshToken)) {
            String email = jwtTokenProvider.getEmailFromToken(refreshToken);
            String newAccessToken = jwtTokenProvider.generateToken(email);

            Map<String, Object> response = new HashMap<>();
            response.put("token", newAccessToken);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token inválido ou expirado");
        }
    }
}
