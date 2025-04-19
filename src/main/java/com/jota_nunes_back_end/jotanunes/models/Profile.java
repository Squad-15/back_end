package com.jota_nunes_back_end.jotanunes.models;

import com.jota_nunes_back_end.jotanunes.enums.Departament;
import com.jota_nunes_back_end.jotanunes.enums.Location;
import com.jota_nunes_back_end.jotanunes.enums.ProfileName;
import com.jota_nunes_back_end.jotanunes.enums.TypeConnection;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileName profileName;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    @Enumerated(EnumType.STRING)
    private Departament department;

    @Enumerated(EnumType.STRING)
    private TypeConnection typeConnection;

    private LocalDate dateAdmission;

    @Enumerated(EnumType.STRING)
    private Location location;

    // Constructor empty
    public Profile() {}

    // Getter and Setter
    public Profile(ProfileName profileName, Departament department, TypeConnection typeConnection, LocalDate dateAdmission, Location location) {
        this.profileName = profileName;
        this.department = department;
        this.typeConnection = typeConnection;
        this.dateAdmission = dateAdmission;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProfileName getProfileName() {
        return profileName;
    }

    public void setProfileName(ProfileName profileName) {
        this.profileName = profileName;
    }

    public Departament getDepartment() {
        return department;
    }

    public void setDepartment(Departament department) {
        this.department = department;
    }

    public TypeConnection getTypeConnection() {
        return typeConnection;
    }

    public void setTypeConnection(TypeConnection typeConnection) {
        this.typeConnection = typeConnection;
    }

    public LocalDate getDateAdmission() {
        return dateAdmission;
    }

    public void setDateAdmission(LocalDate dateAdmission) {
        this.dateAdmission = dateAdmission;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<UserAccount> userAccount;
}