package sia.tacos.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import sia.tacos.enums.TypeOfIngredient;
import sia.tacos.model.Ingredient;
import sia.tacos.model.Order;
import sia.tacos.model.Taco;
import sia.tacos.repository.IngredientRepository;
import sia.tacos.repository.TacoRepository;


import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private TacoRepository designRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository designRepository) {
        this.ingredientRepository = ingredientRepository;
        this.designRepository = designRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = (List<Ingredient>) ingredientRepository.findAll();

        TypeOfIngredient[] typesOfIngredients = TypeOfIngredient.values();
        for (TypeOfIngredient type : typesOfIngredients) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        //model.addAttribute("design", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }

        Taco createdTaco = designRepository.save(taco);
        order.addDesign(createdTaco);
        System.out.println(designRepository.findAll());

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, TypeOfIngredient type) {
        return ingredients.stream()
                .filter(x -> x.getTypeOfIngredient().equals(type))
                .collect(Collectors.toList());
    }
}
