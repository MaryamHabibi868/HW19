package ir.maktabhw19.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString (callSuper = true)
@PrimaryKeyJoinColumn (name = "student_id")
public class Student extends User{

    @Enumerated(EnumType.STRING)
    private RegistrationConfirmation registrationConfirmation;

    @ManyToMany
    private List<Course> courses;

}
