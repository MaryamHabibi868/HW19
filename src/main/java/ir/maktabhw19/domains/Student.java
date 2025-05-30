package ir.maktabhw19.domains;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString (callSuper = true)
@PrimaryKeyJoinColumn (name = "student_id")
public class Student extends User{

    @Enumerated(EnumType.STRING)
    private RegistrationConfirmation registrationConfirmation = RegistrationConfirmation.PENDING;

    @ManyToMany
    private Set<Course> courses = new HashSet<>();

    @OneToOne
    private Answer answer;

}
