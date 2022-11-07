package sia.tacocloud.tacos.repos;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.models.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder,Long> {
}
