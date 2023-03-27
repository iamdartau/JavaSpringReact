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
        RequestPredicate route =
                RequestPredicates
                        .GET("/hello")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN));
        HandlerFunction<ServerResponse> mainPage = serverRequest -> {
            String user = serverRequest.queryParam("user").orElse("nobody");
            return ServerResponse
                    .ok()
                    .render("index", Map.of("user", user));
        };
        return RouterFunctions
                .route(route, greetingHandler::hello)
                .andRoute(RequestPredicates.GET("/"), mainPage);
    }
}
