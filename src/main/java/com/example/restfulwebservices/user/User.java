package com.example.restfulwebservices.user;


import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class User {

    private Integer id;

    @Size(min=2, message = "le nom doit être supérieur à 2 lettres")
    private String name;

    @Past(message = "La date d'anniversaire doit être dans le passé")
    private LocalDate birthDate;
}
