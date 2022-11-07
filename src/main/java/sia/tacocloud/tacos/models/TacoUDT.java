package sia.tacocloud.tacos.models;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;

//UDT (user-defined-type)
@Data
@UserDefinedType("taco")
public class TacoUDT{
    private final String name;
    private final List<IngredientUDT> ingredients;
}
