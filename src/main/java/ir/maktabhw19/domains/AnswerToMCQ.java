package ir.maktabhw19.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class AnswerToMCQ extends Answer{

    private Integer selectedOption;

    @OneToOne
    private Student student;

    @OneToOne
    private MultipleChoiceQuestion question;

}
