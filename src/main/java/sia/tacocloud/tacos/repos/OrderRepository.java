package sia.tacocloud.tacos.repos;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.models.TacoOrder;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<TacoOrder, UUID> {
}
