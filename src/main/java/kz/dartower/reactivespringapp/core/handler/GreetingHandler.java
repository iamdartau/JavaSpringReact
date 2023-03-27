package kz.dartower.reactivespringapp.core.handler;

import kz.dartower.reactivespringapp.core.domain.model.Message;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Flushable;
import java.util.Map;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        BodyInserter<String, ReactiveHttpOutputMessage> inserter
                = BodyInserters.fromValue("hi, spring");
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(inserter);
    }

    public Mono<ServerResponse> helloJson(ServerRequest request) {
        Long start = request
                .queryParam("start")
                .map(Long::valueOf)
                .orElse(0L);

        Long count = request
                .queryParam("count")
                .map(Long::valueOf)
                .orElse(3L);

        Flux<Message> data = Flux.just(
                        "first message",
                        "second message",
                        "third message",
                        "fourth message",
                        "fifth message")
                .skip(start)
                .take(count)
                .map(Message::new);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data, Message.class);
    }

    public Mono<ServerResponse> index(ServerRequest serverRequest) {
        String user = serverRequest.queryParam("user").orElse("nobody");
        return ServerResponse
                .ok()
                .render("index", Map.of("user", user));
    }
}
