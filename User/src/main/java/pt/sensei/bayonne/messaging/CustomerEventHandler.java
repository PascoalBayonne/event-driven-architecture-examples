package pt.sensei.bayonne.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pt.sensei.bayonne.messaging.event.UserCreatedEvent;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Component
@Slf4j
public class CustomerEventHandler {

    private final Sinks.Many<UserCreatedEvent> processor = Sinks.many()
            .multicast()
            .onBackpressureBuffer();

    //EmitterProcessor<UserCreatedEvent> processor = EmitterProcessor.create();

    public void onUserCreatedEvent(UserCreatedEvent userCreatedEvent) {
        log.info("Handling event: {}", userCreatedEvent);
        this.processor.tryEmitNext(userCreatedEvent);
    }

    @Bean
    public Supplier<Flux<UserCreatedEvent>> onUserCreatedEvent() {
        return this.processor::asFlux;
    }
}
