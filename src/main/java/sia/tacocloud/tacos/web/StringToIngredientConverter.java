package sia.tacocloud.tacos.web;

import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import sia.tacocloud.tacos.models.Ingredient;
import sia.tacocloud.tacos.models.IngredientUDT;
import sia.tacocloud.tacos.repos.IngredientRepository;

import java.util.Optional;

@Component
public class StringToIngredientConverter implements Converter<String, IngredientUDT>{

    IngredientRepository ingredientRepository;

    public StringToIngredientConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public IngredientUDT convert(String id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if(ingredient.isEmpty())
            return null;

        return ingredient.map(i-> {
            return new IngredientUDT(i.getName(),i.getType());
        }).get();
    }
}
