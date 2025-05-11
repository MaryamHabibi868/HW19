package ir.maktabhw19.domains;

import jakarta.persistence.*;
import lombok.*;


import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "teacher_id")
public class Teacher extends User{

    @Enumerated(EnumType.STRING)
    private RegistrationConfirmation registrationConfirmation = RegistrationConfirmation.PENDING;

    @OneToMany (mappedBy = "teacher")
    private Set<Course> courses;

    @OneToMany (mappedBy = "teacher")
    private Set<Question> questions;

}
