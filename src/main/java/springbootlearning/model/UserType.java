package springbootlearning.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "user_type")
public class UserType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_type_id")

    private String name;

    private String description;

}
