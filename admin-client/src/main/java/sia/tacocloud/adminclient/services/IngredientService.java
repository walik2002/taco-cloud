package sia.tacocloud.adminclient.services;

import sia.tacocloud.adminclient.models.Ingredient;

public interface IngredientService {

    Iterable<Ingredient> findAll();

    Ingredient addIngredient(Ingredient ingredient);

}