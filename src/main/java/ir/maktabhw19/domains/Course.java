package ir.maktabhw19.domains;

import ir.maktabhw19.domains.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Course extends BaseEntity<Long> {

    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToMany
    private Set<Student> students;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "course")
    private Set<Exam> exams;
}
