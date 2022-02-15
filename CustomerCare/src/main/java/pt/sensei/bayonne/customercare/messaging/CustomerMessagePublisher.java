package pt.sensei.bayonne.customercare.messaging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.sensei.bayonne.customercare.messaging.dto.CustomerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class CustomerMessagePublisher {

    @Bean
    public Sinks.Many<CustomerDTO> sink() {
        return Sinks.many()
                .replay()
                .latest();
    }


    @Bean
    public Supplier<Flux<CustomerDTO>> onCustomerRegistered() {
        return () -> sink()
                .asFlux()
                .cache();
    }
}
