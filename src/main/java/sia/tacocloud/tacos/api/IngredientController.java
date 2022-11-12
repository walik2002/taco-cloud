package sia.tacocloud.tacos.api;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.tacos.models.Ingredient;
import sia.tacocloud.tacos.repos.IngredientRepository;

@RestController
@RequestMapping(path = "/api/ingredients",produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class IngredientController {

    private IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public Iterable<Ingredient> allIngredients(){
        return ingredientRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient){

        return ingredientRepository.save(ingredient);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") String ingredientId){
        ingredientRepository.deleteById(ingredientId);
    }
}
