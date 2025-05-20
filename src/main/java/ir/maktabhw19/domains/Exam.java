package ir.maktabhw19.domains;

import ir.maktabhw19.domains.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Exam extends BaseEntity<Long> {

    private Double score;

    @NotBlank (message = "Start Date of Exam should be entered")
    private LocalDateTime startDate;

    @NotBlank (message = "End date of Exam should be entered")
    private LocalDateTime endDate;

    @ManyToOne
    private Course course;

    @OneToMany (mappedBy = "exam")
    private Set<Question> questions;


}
