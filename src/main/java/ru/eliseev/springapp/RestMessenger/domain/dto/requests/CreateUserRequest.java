package ru.eliseev.springapp.RestMessenger.domain.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.eliseev.springapp.RestMessenger.utils.validation.ExtendedEmailValidator;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @NotNull(message = "Name should not be empty")
    @NotBlank(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String name;

    @NotEmpty(message = "Email should not be empty")
    @ExtendedEmailValidator
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$",
            message = "The password must contain at least eight characters, at least one letter and one number")
    private String password;
}
