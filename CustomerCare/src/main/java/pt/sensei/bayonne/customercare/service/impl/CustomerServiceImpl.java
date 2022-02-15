package pt.sensei.bayonne.customercare.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pt.sensei.bayonne.customercare.domain.Customer;
import pt.sensei.bayonne.customercare.messaging.dto.CustomerDTO;
import pt.sensei.bayonne.customercare.repository.CustomerRepository;
import pt.sensei.bayonne.customercare.service.mapper.CustomerMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl {

    private final Sinks.Many<CustomerDTO> sinks;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AtomicInteger atomicInteger = new AtomicInteger(1);

    @Value("${gorest.uri}")
    public String goRestURI;

    @Scheduled(fixedDelay = 3000)
    public void publish() {
        WebClient.builder()
                .baseUrl(generateURI())
                .build()
                .get()
                .retrieve()
                .bodyToFlux(CustomerDTO.class)
                .onErrorResume(exception -> Mono.error(() -> new Exception(HttpStatus.INTERNAL_SERVER_ERROR.toString())))
                .flatMap(this::createCustomer)
                .subscribe(this.sinks::tryEmitNext);
    }

    private Flux<CustomerDTO> createCustomer(final CustomerDTO customerDTO) {
        final Customer customer = customerMapper.map(customerDTO);
        customerRepository.save(customer);
        log.info(customerDTO.toString());
        return Flux.just(customerDTO);
    }

    private String generateURI() {
        String baseURL = goRestURI.concat("/").concat(String.valueOf(atomicInteger.addAndGet(1)));
        log.info("Building base uri: {}", baseURL);
        return baseURL;
    }
}
