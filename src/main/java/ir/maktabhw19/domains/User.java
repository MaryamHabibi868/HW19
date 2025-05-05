package ir.maktabhw19.domains;

import ir.maktabhw19.domains.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity<Long> {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;

}
