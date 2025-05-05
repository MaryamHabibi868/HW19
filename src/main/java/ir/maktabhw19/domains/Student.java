package ir.maktabhw19.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString (callSuper = true)
@PrimaryKeyJoinColumn (name = "student_id")
public class Student extends User{

}
