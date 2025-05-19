package com.example.example1.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


//@NotNull - check if field is not null, it allows empty and 0 values
//@NotEmpty - check if field is not null and its length is greater than 0
//@NotBlank - check if field is not null and trimmed length is greather than 0

@Data
public class Contact {
    @NotBlank(message = "name must not be blank")
    @Size(min=3,message = "Name must be atleast 3 characters long")
    private String name;

    @NotBlank(message = "mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNum;

    @NotBlank(message = "email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;


    @NotBlank(message = "subject must not be blank")
    @Size(min=5,message = "subject must be atleast 5 characters long")
    private String subject;

    @NotBlank(message = "message must not be blank")
    @Size(min=10,message = "Message must be atleast 10 characters long")
    private String message;
}
