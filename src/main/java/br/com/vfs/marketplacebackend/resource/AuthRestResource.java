package br.com.vfs.marketplacebackend.resource;

import br.com.vfs.marketplacebackend.message.request.LoginRequest;
import br.com.vfs.marketplacebackend.message.request.SignUpRequest;
import br.com.vfs.marketplacebackend.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestResource {

    private final UserServiceImpl userService;

    public AuthRestResource(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return userService.registerUser(signUpRequest);
    }
}
