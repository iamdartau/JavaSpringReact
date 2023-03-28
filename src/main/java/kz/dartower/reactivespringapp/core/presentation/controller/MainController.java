package kz.dartower.reactivespringapp.core.presentation.controller;

import kz.dartower.reactivespringapp.core.domain.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/controller")
public class MainController {

    @GetMapping
    public Flux<Message> list(@RequestParam(defaultValue = "0") Long start,
                              @RequestParam(defaultValue = "3") Long count) {

        return Flux.just(
                        "first message",
                        "second message",
                        "third message",
                        "fourth message",
                        "fifth message")
                .skip(start)
                .take(count)
                .map(Message::new);
    }
}
