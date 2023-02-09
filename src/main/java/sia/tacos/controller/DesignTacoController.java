package sia.tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sia.tacos.enums.TypeOfIngredient;
import sia.tacos.model.Ingredient;
import sia.tacos.model.Taco;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "pszenna", TypeOfIngredient.WRAP),
                new Ingredient("COTO", "kukurydziana", TypeOfIngredient.WRAP),
                new Ingredient("GRBF", "mielona wołowina", TypeOfIngredient.PROTEIN),
                new Ingredient("CARN", "kawałki mięsa", TypeOfIngredient.PROTEIN),
                new Ingredient("TMTO", "pomidory pokrojone w kostkę", TypeOfIngredient.VEGGIES),
                new Ingredient("LETC", "sałata", TypeOfIngredient.VEGGIES),
                new Ingredient("CHED", "cheddar", TypeOfIngredient.CHEESE),
                new Ingredient("JACK", "Monterey Jack", TypeOfIngredient.CHEESE),
                new Ingredient("SLSA", "pikantny sos pomidorowy", TypeOfIngredient.SAUCE),
                new Ingredient("SRCR", "śmietana", TypeOfIngredient.SAUCE)
        );

        TypeOfIngredient[] typesOfIngredients = TypeOfIngredient.values();
        for (TypeOfIngredient type : typesOfIngredients) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(Taco taco) {
        //Logika w rodziale 3
        log.info("Przetwarzanie projektu taco: " + taco);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, TypeOfIngredient type) {
        return ingredients.stream()
                .filter(x -> x.getTypeOfIngredient().equals(type))
                .collect(Collectors.toList());
    }
}
