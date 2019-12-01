package edu.com.chatbotsoftI;

import static spark.Spark.post;
import static spark.Spark.port;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainClassTest {
    private static Gson gson = new Gson();

    static class CreatePaymentBody {
        @SerializedName("items")
        Object[] items;

        @SerializedName("currency")
        String currency;

        public Object[] getItems() {
            return items;
        }

        public String getCurrency() {
            return currency;
        }
    }

    static class CreatePaymentResponse {
        private String publishableKey;
        private String clientSecret;

        public CreatePaymentResponse(String publishableKey, String clientSecret) {
            this.publishableKey = publishableKey;
            this.clientSecret = clientSecret;
        }
    }

    static int calculateOrderAmount(Object[] items) {
        // Replace this constant with a calculation of the order's amount
        // Calculate the order total on the server to prevent
        // users from directly manipulating the amount on the client
        return 1400;
    }

    @Test
    public void main() {
        port(4242);

        Stripe.apiKey = "sk_test_vceSL7gg1Ggwjl8Yl5Pxd7Ob006KWSLj5y";

        post("/create-payment-intent", (request, response) -> {
            response.type("application/json");

            CreatePaymentBody postBody = gson.fromJson(request.body(), CreatePaymentBody.class);
            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setCurrency(postBody.getCurrency()).setAmount(new Long(calculateOrderAmount(postBody.getItems())))
                    .build();
            // Create a PaymentIntent with the order amount and currency
            PaymentIntent intent = PaymentIntent.create(createParams);
            // Send publishable key and PaymentIntent details to client
            return gson.toJson(new CreatePaymentResponse("pk_test_mR5ZZ8Gj3x33mliURSt98yLu00xxHJoB8j",
                    intent.getClientSecret()));
        });


    }
}