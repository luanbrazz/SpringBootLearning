package springbootlearning.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
//@Getter
//@Setter
//@ToString

@EqualsAndHashCode(callSuper = false)

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscriptions_type")
public class SubscriptionType extends RepresentationModel<SubscriptionType> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscriptions_type_id")
    private Long id;

    private String name;

    @Column(name = "access_months")
    private Long accessMonth;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "product_key", unique = true)
    private String productKey;
}
