package ir.maktabhw19.domains;

import ir.maktabhw19.domains.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Exam extends BaseEntity<Long> {

    private Double givenScore;

    @NotBlank (message = "Start Date of Exam should be entered")
    private LocalDateTime startDate;

    @NotBlank (message = "End date of Exam should be entered")
    private LocalDateTime endDate;

    @ManyToOne
    private Course course;

    @OneToMany (mappedBy = "exam" , cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<>();

  public Double calculateDefaultExamScore() {
      if (questions == null || questions.isEmpty()) {
          return 0.0;
      }
      return questions.stream()
              .filter(q -> q.getDefaultScore() != null)
              .mapToDouble(Question :: getDefaultScore)
              .sum();
  }

    public Double calculateGivenScore() {
        if (questions == null || questions.isEmpty()) {
            return 0.0;
        }
        return questions.stream()
                .filter(q -> q.getGivenScore() != null)
                .mapToDouble(Question :: getGivenScore)
                .sum();
    }

    public void addQuestion(Question question) {
        questions.add(question);
        question.setExam(this);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
        question.setExam(null);
    }
}
