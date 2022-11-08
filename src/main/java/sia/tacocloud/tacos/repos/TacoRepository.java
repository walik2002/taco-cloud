package sia.tacocloud.tacos.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import sia.tacocloud.tacos.models.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco,Long> {
}
