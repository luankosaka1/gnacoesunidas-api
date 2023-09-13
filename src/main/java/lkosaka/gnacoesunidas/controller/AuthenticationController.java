package lkosaka.gnacoesunidas.controller;

import jakarta.validation.Valid;
import lkosaka.gnacoesunidas.domain.user.User;
import lkosaka.gnacoesunidas.dto.user.AuthenticationRequestDto;
import lkosaka.gnacoesunidas.dto.user.AuthenticationResponseDto;
import lkosaka.gnacoesunidas.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequestDto dto) {
        var token = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var authentication = manager.authenticate(token);
        var jwt = tokenService.buildToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new AuthenticationResponseDto(jwt));
    }
}
