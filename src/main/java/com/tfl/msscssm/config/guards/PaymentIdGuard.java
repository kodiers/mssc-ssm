package com.tfl.msscssm.config.guards;

import com.tfl.msscssm.domain.PaymentEvent;
import com.tfl.msscssm.domain.PaymentState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

import static com.tfl.msscssm.service.PaymentServiceImpl.MESSAGE_ID_HEADER;

@Component
public class PaymentIdGuard implements Guard<PaymentState, PaymentEvent> {

    @Override
    public boolean evaluate(StateContext<PaymentState, PaymentEvent> stateContext) {
        return stateContext.getMessageHeader(MESSAGE_ID_HEADER) != null;
    }
}
