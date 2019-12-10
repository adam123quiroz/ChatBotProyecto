package edu.com.chatbotsoftI;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MainClassTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainClassTest.class);
    @Test
    public void main() {
        try {
            Stripe.apiKey = "sk_test_vceSL7gg1Ggwjl8Yl5Pxd7Ob006KWSLj5y";
            Map<String, Object> params = new HashMap<>();
            params.put("limit", 3);

            PaymentIntentCollection paymentIntents =
                    PaymentIntent.list(params);
            LOGGER.info("Pagos {}", paymentIntents);
        } catch (StripeException e) {
            e.printStackTrace();
        }


    }
}