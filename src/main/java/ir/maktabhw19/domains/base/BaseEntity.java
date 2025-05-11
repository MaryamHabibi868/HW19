package ir.maktabhw19.domains.base;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity <ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
}
