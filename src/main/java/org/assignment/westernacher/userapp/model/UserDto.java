package org.assignment.westernacher.userapp.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class UserDto {

    private UUID id;

    @NotBlank(message = "First name could not be blank")
    private String firstName;
    @NotBlank(message = "Last name could not be blank")
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    @Past(message = "Date of birth should be in the past")
    private LocalDate birthDate;

}
