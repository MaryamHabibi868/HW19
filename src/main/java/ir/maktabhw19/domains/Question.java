package ir.maktabhw19.domains;

import ir.maktabhw19.domains.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Question extends BaseEntity <Long> {

    private Double defaultScore;

    @Enumerated (EnumType.STRING)
    private TypesOfExamQuestions typeQuestion;

    @ManyToOne
    private Exam exam;

    @ManyToOne
    private Teacher teacher;

}
