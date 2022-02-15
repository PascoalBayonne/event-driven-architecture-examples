package pt.sensei.bayonne.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pt.sensei.bayonne.controller.mapper.BankAccountMapper;
import pt.sensei.bayonne.domain.User;
import pt.sensei.bayonne.messaging.CustomerEventHandler;
import pt.sensei.bayonne.messaging.event.UserCreatedEvent;
import pt.sensei.bayonne.service.BankAccountService;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankAccountServiceImplement implements BankAccountService {

    private final CustomerEventHandler customerEventHandler;
    private final BankAccountMapper bankAccountMapper;

    @Override
    public void create(User user) {
        UserCreatedEvent userCreatedEvent = bankAccountMapper.mapToUserCreatedEvent(user);
        customerEventHandler.onUserCreatedEvent(userCreatedEvent);
    }
}
