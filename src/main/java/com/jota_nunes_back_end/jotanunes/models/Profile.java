package com.jota_nunes_back_end.jotanunes.models;

import com.jota_nunes_back_end.jotanunes.enums.ProfileName;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileName profileName;

    // Constructor empty
    public Profile() {}

    // Getter and Setter
    public Profile(ProfileName profileName) {
        this.profileName = profileName;
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

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<UserAccount> userAccount;
}