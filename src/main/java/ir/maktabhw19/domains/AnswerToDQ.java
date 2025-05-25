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
public class AnswerToDQ extends Answer {

    private String answer;

    @OneToOne
    private DescriptiveQuestion question;

    @OneToOne
    private Student student;
}
