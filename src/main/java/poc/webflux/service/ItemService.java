package poc.webflux.service;

import poc.webflux.model.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemService {

    Flux<Item> findAll();

    Mono<Item> findById(String id);

    Mono<Item> save(Item item);

    void deleteById(String id);

}
