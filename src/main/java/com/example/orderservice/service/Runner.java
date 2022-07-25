package com.example.orderservice.service;

import com.example.orderservice.enumerate.OrderEvents;
import com.example.orderservice.enumerate.OrderStates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Runner implements ApplicationRunner {
    private final StateMachineFactory<OrderStates, OrderEvents> factory;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Long orderId = 12345L;
        StateMachine<OrderStates, OrderEvents> stateMachine = this.factory.getStateMachine(Long.toString(orderId));
        stateMachine.getExtendedState().getVariables().putIfAbsent("orderId",orderId);
        stateMachine.start();

        log.info("current state: {}", stateMachine.getState().getId().name());
    }
}
