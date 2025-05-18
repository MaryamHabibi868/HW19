package ir.maktabhw19.domains;

import ir.maktabhw19.domains.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table (name = "users")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity<Long> {

    @NotBlank(message = "First Name should be entered")
    private String firstName;

    @NotBlank(message = "Last Name should be entered")
    private String lastName;

    @NotBlank(message = "User Name should be entered")
    private String userName;

    @NotBlank(message = "Password should be entered")
    private String password;

}
