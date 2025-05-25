package ir.maktabhw19.domains;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private List<String> options = new ArrayList<>();

    @NotBlank(message = "Index of correct option should be entered")
    private Integer correctOptionIndex;

    public String getCorrectAnswer() {
        return options.get(correctOptionIndex);
    }
}

