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
    @SequenceGenerator(
            name = "id_seq_gen",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY , generator = "id_seq_gen")
    private Long id;
}
