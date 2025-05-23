package ir.maktabhw19.domains;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("DQ")
@Getter
@Setter
@SuperBuilder
/*
@AllArgsConstructor
*/
@NoArgsConstructor
@ToString(callSuper = true)
public class DescriptiveQuestion extends Question {
}
