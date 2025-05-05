package ir.maktabhw19.domains;

import ir.maktabhw19.domains.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Course extends BaseEntity<Long> {

    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

    private List<Student> students;

    private List<Teacher> teacher;
}
