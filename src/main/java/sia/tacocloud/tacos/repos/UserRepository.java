package sia.tacocloud.tacos.repos;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.models.User;

public interface UserRepository extends CrudRepository<User,Long> {

    User findByUsername(String username); // Spring Data JPA автоматически генерирует реализацию этого интерфейса во время выполнения
}
