package ir.maktabhw19.domains;

import ir.maktabhw19.domains.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "question_type")
public class Question extends BaseEntity <Long> {

    private String questionTitle;

    private String questionStatement;

    private Double defaultScore;

    @ManyToOne
    private Exam exam;

    @ManyToOne
    private Teacher teacher;

}
