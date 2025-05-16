package chat.help.help_chat.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(

        @NotBlank
        @Email
        String email,

        @NotBlank
        String password
) {
}
