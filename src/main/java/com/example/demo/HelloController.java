package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * This is json stream class.
 * https://spring.io/blog/2016/11/28/going-reactive-with-spring-data
 * https://github.com/MarcoGhise/ReactiveMongoPerformance
 */
@RestController
public class HelloController {
    @GetMapping("/")
    Flux<String> hello() {
        return Flux.just("Hello", "World");
    }

    /*
    @GetMapping(path = "/stream", produces = "application/stream+json")
    Flux<Item> allItems() {

        List<Item> a = new ArrayList<>();
        for (int i= 0;i > 1000;i++) {
            Item item = new Item();
            item.setId(String.valueOf(i));
            item.setName("AAAABBBDDD_" + 1);

        }

        return Flux.fromIterable(a);

    }
    */

    @GetMapping("/stream")
    Flux<Map<String, Integer>> stream() {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1); // Java8の無限Stream
        return Flux.fromStream(stream.limit(10))
                .map(i -> Collections.singletonMap("value", i));

    }

}
