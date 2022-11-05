package sia.tacocloud.tacos.repos;

import sia.tacocloud.tacos.models.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
