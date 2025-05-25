package ir.maktabhw19.domains;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

@Entity
@DiscriminatorValue("MCQ")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class MultipleChoiceQuestion extends Question {

    private Map<Integer, String> options = new HashMap<>();

    @NotBlank(message = "Index of correct option should be entered")
    private Integer correctOptionIndex;

    public String getCorrectAnswer() {
        return options.get(correctOptionIndex);
    }
}

