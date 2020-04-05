package poc.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poc.webflux.model.Item;
import poc.webflux.service.ItemService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

//@RestController
@RequestMapping("api/v1/items")
public class ItemController {

    private ItemService service;

    @Autowired
    ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<Flux<Item>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Mono<Item>> findById(@PathVariable("id") String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Mono<Item>> save(@RequestBody Item product) {
        return new ResponseEntity<>(service.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@RequestBody String id) {
       service.deleteById(id);
       return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Item>> findByEvents() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
        Flux<Item> events = service.findAll();
        return Flux.zip(interval, events);
    }

}
