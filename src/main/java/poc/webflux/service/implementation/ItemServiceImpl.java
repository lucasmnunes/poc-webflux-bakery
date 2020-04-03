package poc.webflux.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poc.webflux.model.Item;
import poc.webflux.repository.ItemRepository;
import poc.webflux.service.ItemService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository repository;

    @Autowired
    ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Item> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Item> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Item> save(Item item) {
        return repository.save(item);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

}
