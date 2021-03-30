package com.tfl.msscssm.config.actions;

import com.tfl.msscssm.domain.PaymentEvent;
import com.tfl.msscssm.domain.PaymentState;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Random;

import static com.tfl.msscssm.service.PaymentServiceImpl.MESSAGE_ID_HEADER;

@Component
public class PreAuthAction implements Action<PaymentState, PaymentEvent> {
    @Override
    public void execute(StateContext<PaymentState, PaymentEvent> stateContext) {
        System.out.println("Preauth called");
        if (new Random().nextInt(10) < 8) {
            System.out.println("Approved");
            stateContext.getStateMachine().sendEvent(MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_APPROVED)
                    .setHeader(MESSAGE_ID_HEADER, stateContext.getMessageHeader(MESSAGE_ID_HEADER))
                    .build());
        } else {
            System.out.println("Declined");
            stateContext.getStateMachine().sendEvent(MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_DECLINED)
                    .setHeader(MESSAGE_ID_HEADER, stateContext.getMessageHeader(MESSAGE_ID_HEADER))
                    .build());
        }
    }
}
