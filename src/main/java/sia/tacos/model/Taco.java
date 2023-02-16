package sia.tacos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=5, message = "Nazwa musi składać się z przynajmniej pięciu znaków!")
    private String name;

    private Date createdAt;

    @ManyToMany(targetEntity = Ingredient.class)
    @NotNull(message = "Musisz wybrać przynajmniej jeden składnik!")
    private List<Ingredient> ingredients;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
