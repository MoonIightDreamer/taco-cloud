package tacos.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tacos.Ingredient;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class RestClient {

    private RestTemplate rest;

    // GET
    public Ingredient getIngredientById(String ingredientId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        URI url = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8433/ingredients/{id}")
                .build(urlVariables);
        ResponseEntity<Ingredient> response = rest.getForEntity(url, Ingredient.class);
        log.info("Fetched time: {}",
                response.getHeaders().getDate());
        return response.getBody();
    }

    // PUT
    public void updateIngredient(Ingredient ingredient) {
        rest.put("http://localhost:8433/ingredients/{id}", ingredient, ingredient.getId());
    }

    // DELETE
    public void deleteIngredient(Ingredient ingredient) {
        rest.delete("http://localhost:8433/ingredients/{id}", ingredient.getId());
    }

    // POST
    public Ingredient postIngredient(Ingredient ingredient) {
        ResponseEntity<Ingredient> responseEntity
                = rest.postForEntity("http://localhost:8433/ingredients/", ingredient, Ingredient.class);
        log.info("New resource created at {}",
                responseEntity.getHeaders().getLocation());
        return responseEntity.getBody();
    }
}
