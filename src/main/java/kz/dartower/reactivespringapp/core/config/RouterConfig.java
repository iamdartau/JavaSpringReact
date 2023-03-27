package kz.dartower.reactivespringapp.core.config;

import kz.dartower.reactivespringapp.core.handler.GreetingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;

import java.util.Map;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {
        var helloRoute = RequestPredicates
                .GET("/hello")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN));
        var indexRoute = RequestPredicates
                .GET("/");
        var helloRouteJson = RequestPredicates
                .GET("/helloJson")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

        return RouterFunctions
                .route(helloRoute, greetingHandler::hello)
                .andRoute(indexRoute, greetingHandler::index)
                .andRoute(helloRouteJson, greetingHandler::helloJson);
    }
}
