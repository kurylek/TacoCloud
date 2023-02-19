package sia.tacos.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    @NotBlank(message = "Podanie imienia i nazwiska jest obowiązkowe!")
    private String clientName;

    @NotBlank(message = "Podanie ulicy jest obowiązkowe!")
    private String street;

    @NotBlank(message = "Podanie miejscowości jest obowiązkowe!")
    private String city;

    @NotBlank(message = "Podanie województwa jest obowiązkowe!")
    private String state;

    @NotBlank(message = "Podanie kodu pocztowego jest obowiązkowe!")
    private String zip;

    @CreditCardNumber(message = "To nie jest prawidłowy numer karty kredytowej!")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message = "Wartość musi być w formacie MM/RR!")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Nieprawidłowy kod CVV!")
    private String ccCVV;

    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne
    private User user;

    public void addDesign(Taco design) {
        this.tacos.add(design);
    }

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
