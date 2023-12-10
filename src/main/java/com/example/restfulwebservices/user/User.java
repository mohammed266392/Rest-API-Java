package com.example.restfulwebservices.user;


import com.example.restfulwebservices.post.Post;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "user_details")

public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2, message = "le nom doit être supérieur à 2 lettres")
    //@JsonProperty sert juste à custom le rendu de l'API, c'est un custom
    @JsonProperty("user_name")
    private String name;

    @Past(message = "La date d'anniversaire doit être dans le passé")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user" )
    private List<Post> posts;

    public User(int i, String mohammed, LocalDate minusYears) {
        this.id = i;
        this.name=mohammed;
        this.birthDate=minusYears;
    }
}
