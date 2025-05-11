package ir.maktabhw19.domains;

import ir.maktabhw19.domains.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;


import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Exam extends BaseEntity<Long> {

    private Double score;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ManyToOne
    private Course course;

    @OneToMany (mappedBy = "exam")
    private Set<Question> questions;


}
