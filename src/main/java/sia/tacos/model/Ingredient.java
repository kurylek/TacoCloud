package sia.tacos.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import sia.tacos.enums.TypeOfIngredient;

@Data
@RequiredArgsConstructor
public class Ingredient {

    private final String id;
    private final String name;
    private final TypeOfIngredient typeOfIngredient;
}
