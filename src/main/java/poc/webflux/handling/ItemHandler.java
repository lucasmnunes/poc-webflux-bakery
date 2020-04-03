package poc.webflux.handling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import poc.webflux.model.Item;
import poc.webflux.service.ItemService;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class ItemHandler {

    private ItemService service;

    @Autowired
    ItemHandler(ItemService service) {
        this.service = service;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Item.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findById(request.pathVariable("id")), Item.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(request.bodyToMono(Item.class).flatMap(service::save), Item.class));
    }

}
