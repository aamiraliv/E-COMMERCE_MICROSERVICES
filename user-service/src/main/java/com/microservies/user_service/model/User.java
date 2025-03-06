package com.microservies.user_service.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username must not be blank")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Column(nullable = false)
    private String password;

//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public User(String password, String email, String username, Long id) {
//        this.password = password;
//        this.email = email;
//        this.username = username;
//        this.id = id;
//    }
}
