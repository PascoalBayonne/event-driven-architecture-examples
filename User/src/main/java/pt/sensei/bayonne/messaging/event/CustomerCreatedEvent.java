package pt.sensei.bayonne.messaging.event;

public record CustomerCreatedEvent(Long id,
                                   String firstname,
                                   String lastname,
                                   String age) {
}
