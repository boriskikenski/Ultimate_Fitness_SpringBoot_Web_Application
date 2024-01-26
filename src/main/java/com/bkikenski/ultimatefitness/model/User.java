package com.bkikenski.ultimatefitness.model;

import com.bkikenski.ultimatefitness.model.enumerations.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    private String surname;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Sex sex;

    private int age;

    private int height;

    private float startWeight;

    @Enumerated(value = EnumType.STRING)
    private FitnessLevels level;

    @Enumerated(value = EnumType.STRING)
    private Goals goal;

    @OneToMany(mappedBy = "user")
    private List<WeeklyResults> results;

    @Enumerated(value = EnumType.STRING)
    private FitnessPlans currentFitnessPlan;

    @OneToMany(mappedBy = "user")
    private List<Training> trainingPlan;

    @OneToOne
    private DietPlan dietPlan;

    private int dailySteps;

    public float getCurrentWeight() {
        if (this.results.isEmpty())
            return this.startWeight;
        else
            return this.results.get(this.results.size() - 1).getBodyWeight();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
