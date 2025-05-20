package ir.maktabhw19.domains;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("MCQ")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
public class MultipleChoiceQuestion extends Question {

    @ElementCollection
    private List<String> options = new ArrayList<>();

    private Integer correctOptionIndex;

    public String getCorrectAnswer() {
        return options.get(correctOptionIndex);
    }
}

