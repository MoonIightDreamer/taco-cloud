package tacos.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.User;
import tacos.data.IngredientRepository;
import tacos.data.UserRepository;

@Configuration
@Profile("!prod")
public class DatabaseInitializer {

    @Bean
    public CommandLineRunner userLoader(UserRepository repo) {
        return args -> {
            repo.deleteAll();
            repo.save(new User("user", "123",
                    null, null, null, null, null, null));
            repo.save(new User("user2", "123",
                            null, null, null, null, null, null));
        };
    }

    @Bean
    public CommandLineRunner ingredientLoader(IngredientRepository repo) {
        return args -> {
            repo.deleteAll();
            repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
            repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
            repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
            repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
            repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
            repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
            repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
            repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
            repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
            repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
        };
    }
}
