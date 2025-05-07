package ir.maktabhw19.domains.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity <ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
}
