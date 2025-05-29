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
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "teacher_id")
public class Teacher extends User{

    @Enumerated(EnumType.STRING)
    private RegistrationConfirmation registrationConfirmation = RegistrationConfirmation.PENDING;

    @OneToMany (mappedBy = "teacher")
    private Set<Course> courses = new HashSet<>();

    @OneToMany (mappedBy = "teacher")
    private Set<Question> questions = new HashSet<>();

}
