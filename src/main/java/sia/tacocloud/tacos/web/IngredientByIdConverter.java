package sia.tacocloud.tacos.web;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sia.tacocloud.tacos.models.Ingredient;
import sia.tacocloud.tacos.models.Ingredient.Type;
import sia.tacocloud.tacos.repos.IngredientRepository;

@Component
    public class IngredientByIdConverter implements Converter<String, Ingredient> {
        private IngredientRepository ingredientRepository;

        @Autowired
        public IngredientByIdConverter(IngredientRepository ingredientRepository){
            this.ingredientRepository = ingredientRepository;
        }
        @Override
        public Ingredient convert(String id) {

            return ingredientRepository.findById(id).orElse(null);
        }
}
