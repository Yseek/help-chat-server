package chat.help.help_chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignUpController {

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest) {

        String email = signUpRequest.email;
        String password = signUpRequest.password;
        System.out.println("email = " + email);
        System.out.println("password = " + password);

        return ResponseEntity.ok("Sign up successful");
    }

    record SignUpRequest(String email, String password) {}
}
