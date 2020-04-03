package poc.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import poc.webflux.model.Item;

public interface ItemRepository extends ReactiveMongoRepository<Item, String> {
}
