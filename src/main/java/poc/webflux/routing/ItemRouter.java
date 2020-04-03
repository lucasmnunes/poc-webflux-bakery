package poc.webflux.routing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import poc.webflux.handling.ItemHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ItemRouter {

    private static final String API_SUFFIX = "/api/v1";

    @Bean
    public RouterFunction<ServerResponse> route(ItemHandler handler) {
        return RouterFunctions
                .route(GET(API_SUFFIX + "/items").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
                .andRoute(GET(API_SUFFIX + "/items/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::findById)
                .andRoute(POST(API_SUFFIX + "/items").and(accept(MediaType.APPLICATION_JSON)), handler::save);
    }

}
