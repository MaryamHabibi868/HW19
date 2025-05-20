package ir.maktabhw19.domains.base;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity <ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
}
