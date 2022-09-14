package ru.eliseev.springapp.RestMessenger.domain.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.eliseev.springapp.RestMessenger.utils.validation.ExtendedEmailValidator;
import ru.eliseev.springapp.RestMessenger.utils.validation.NullOrNotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    @NullOrNotBlank
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String name;

    @ExtendedEmailValidator
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$",
            message = "The password must contain at least eight characters, at least one letter and one number")
    private String password;
}
