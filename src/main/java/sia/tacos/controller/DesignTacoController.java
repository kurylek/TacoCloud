package sia.tacos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sia.tacos.enums.TypeOfIngredient;
import sia.tacos.model.Ingredient;
import sia.tacos.model.Taco;
import sia.tacos.repository.IngredientRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/design")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = (List<Ingredient>) ingredientRepository.findAll();

        TypeOfIngredient[] typesOfIngredients = TypeOfIngredient.values();
        for (TypeOfIngredient type : typesOfIngredients) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco taco, Errors errors) {
        if (errors.hasErrors()) {
            return "design";
        }
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
