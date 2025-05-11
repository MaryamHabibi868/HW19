package ir.maktabhw19.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "manager_id")
public class Manager extends User{
}
