package com.gmail.podkutin.dmitry.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity {
    @Column(name = "email")
    private String email;
    @Column(name = "firs_name")
    private String firsName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return email.equals(user.email) && firsName.equals(user.firsName) && lastName.equals(user.lastName) && password.equals(user.password) && role == user.role && status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, firsName, lastName, password, role, status);
    }
}
