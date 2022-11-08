package sia.tacocloud.tacos.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.models.TacoOrder;
import sia.tacocloud.tacos.models.User;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder,Long> {
    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
