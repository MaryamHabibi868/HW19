package ir.maktabhw19.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
/*
@AllArgsConstructor
*/
@NoArgsConstructor
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "manager_id")
public class Manager extends User{
}
