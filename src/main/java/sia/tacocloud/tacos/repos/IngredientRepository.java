package sia.tacocloud.tacos.repos;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.models.Ingredient;


public interface IngredientRepository extends CrudRepository<Ingredient,String> {

}
