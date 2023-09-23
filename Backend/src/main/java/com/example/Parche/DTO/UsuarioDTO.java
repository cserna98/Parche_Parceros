package com.example.Parche.DTO;

import com.example.Parche.entity.Parche;

import java.util.Set;

public class UsuarioDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Set<Parche> parches;

    public UsuarioDTO(Long id, String firstname, String lastname, String email, Set<Parche> parches) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.parches = parches;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Parche> getParches() {
        return parches;
    }

    public void setParches(Set<Parche> parches) {
        this.parches = parches;
    }
}