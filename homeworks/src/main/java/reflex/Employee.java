package reflex;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
// @JsonEntity
public class Employee {

//    @JsonField(value = "name")
    private String firstName;

    private String lastName;

//    @JsonField
    private int age;

//    private String[] vaslues;
//    private Home home;
}
