package ir.maktabhw19.domains;

import ir.maktabhw19.domains.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "question_type")
public class Question extends BaseEntity <Long> {

    @NotBlank (message = "Title of question should be entered")
    private String questionTitle;

    @NotBlank (message = "Statement of question should be entered")
    private String questionStatement;

    @ManyToOne
    private Exam exam;

    @ManyToOne
    private Teacher teacher;

    @OneToMany (mappedBy = "question")
    private Set<Answer> answers = new HashSet<>();

}
