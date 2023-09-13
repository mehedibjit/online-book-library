package com.mehedi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Entity
//@Table(name = "user")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
//    private Long userId;
//    @Column(name = "first_name")
//    private String firstName;
//    @Column(name = "last_name")
//    private String lastName;
//    @Column(name = "email")
//    private String email;
//    @Column(name = "password")
//    private String password;
//    @Column(name = "address")
//    private String address;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<Book> books;
//}


@Entity
@Table(name="User")
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "first_name",nullable = false)
    @NotEmpty
    private String firstName;
    @Column(name = "last_name",nullable = false)
    @NotEmpty
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty
    @Email
    private String email;
    @Column(name = "password", nullable = false)
    @NotEmpty
    private String password;
    @Column(name = "address", nullable = false)
    @NotEmpty
    private String address;
    public enum Role {
        CUSTOMER,
        ADMIN
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
}
