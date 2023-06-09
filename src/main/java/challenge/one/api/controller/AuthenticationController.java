package challenge.one.api.controller;

import challenge.one.api.domain.user.CreateUserDto;
import challenge.one.api.domain.user.User;
import challenge.one.api.infra.security.DataJwtTokenDto;
import challenge.one.api.infra.security.TokenJwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenJwtService tokenJwtService;


    @PostMapping
    public ResponseEntity login(@RequestBody @Valid CreateUserDto data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var jwtToken = tokenJwtService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DataJwtTokenDto(jwtToken));
    }
}
