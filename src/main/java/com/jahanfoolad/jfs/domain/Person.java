package com.jahanfoolad.jfs.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public class Person extends AbstractEntity implements Cloneable {

    @Column(unique = true)
    private String userName;

    @Column
    private String cellPhone;

    @Column
    private String email;

    @Column
    private Boolean verified;

    @Column
    private Boolean activated;

    @Column
    private String password;

    @Column
    private String confirmationCode;

    @Column
    private boolean isAdmin = false;

    @Column
    private boolean isActive = true;

    @OneToMany(cascade = {CascadeType.MERGE} , fetch = FetchType.EAGER)
    private List<Contact> contactList = new ArrayList<>();

    @Column
    Date lastLoginDate;

    @Column
    Boolean isAuthorizationChanged = false;

    @ManyToMany(fetch = FetchType.EAGER ,cascade = CascadeType.MERGE)
    @JoinTable(name = "role_person",
            joinColumns =
            @JoinColumn(name = "person_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "role_id", referencedColumnName = "id"))
    public List<Role> role;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
