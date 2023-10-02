package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.TacoOrder;

import javax.validation.Valid;
import java.util.List;

import static java.util.Arrays.asList;
import static tacos.Ingredient.Type.*;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        var ingredients = asList(
                new Ingredient("FLTO", "Flour Tortilla", WRAP),
                new Ingredient("COTO", "Corn Tortilla", WRAP),
                new Ingredient("GRBF", "Ground Beef", PROTEIN),
                new Ingredient("CARN", "Carnitas", PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", VEGGIES),
                new Ingredient("LETC", "Lettuce", VEGGIES),
                new Ingredient("CHED", "Cheddar", CHEESE),
                new Ingredient("JACK", "Monterrey Jack", CHEESE),
                new Ingredient("SLSA", "Salsa", SAUCE),
                new Ingredient("SRCR", "Sour Cream", SAUCE)
        );

        var types = Type.values();
        for(var type: types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        System.out.println(model.getAttribute("wrap"));
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(ingr -> ingr.getType().equals(type))
                .toList();
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco,
                              Errors errors,
                              @ModelAttribute TacoOrder tacoOrder) {
        if(errors.hasErrors()) {
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        
        return "redirect:/orders/current";
    }
}
