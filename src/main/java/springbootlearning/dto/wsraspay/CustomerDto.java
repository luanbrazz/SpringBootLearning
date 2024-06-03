package springbootlearning.dto.wsraspay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private String cpf;

    private String email;

    private String firstName;

    private String id;

    private String lastName;
}
