package edu.com.chatbotsoftI.auxiliar;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.param.InvoiceUpdateParams;
import com.stripe.param.PaymentIntentUpdateParams;
import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.commands.Command;
import edu.com.chatbotsoftI.dao.EvePaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.AnswerPreCheckoutQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SequencePayment extends Sequence {
    private static final Logger LOGGER = LoggerFactory.getLogger(SequencePayment.class);
    private String email;
    private EvePaymentRepository evePaymentRepository;

    public SequencePayment(EvePaymentRepository evePaymentRepository) {
        super(true, 2, 0);
        this.evePaymentRepository = evePaymentRepository;
    }

    @Override
    public void runSequence(Update update, BoltonBot bot) {
        if (getStepNow() < getNumberSteps()) {
            switch (getStepNow()) {
                case 0:
                    //TODO: implement logic
                    try {
                        AnswerPreCheckoutQuery answerPreCheckoutQuery =
                            new AnswerPreCheckoutQuery(update.getPreCheckoutQuery().getId(), true);
                        answerPreCheckoutQuery.validate();
                        bot.execute(answerPreCheckoutQuery);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    //TODO: implements second step
                    if (update.getMessage().getSuccessfulPayment() != null) {
                        try {
                            Stripe.apiKey = Command.STRIPE_KEY;
                            email = update.getMessage().getSuccessfulPayment().getOrderInfo().getEmail();

                            Map<String, Object> params_1 = new HashMap<>();
                            params_1.put("limit", 100);
                            PaymentIntentCollection paymentIntents =
                                    PaymentIntent.list(params_1);
                            List<PaymentIntent> paymentIntentList = paymentIntents.getData();
                            PaymentIntent payment = null;
                            for (PaymentIntent paymentIntent :
                                    paymentIntentList) {
                                if (paymentIntent.getCharges().getData().get(0).getId().equalsIgnoreCase(
                                        update.getMessage().getSuccessfulPayment().getProviderPaymentChargeId())) {
                                    payment = paymentIntent;
                                    break;
                                }
                            }
                            LOGGER.info("payment {}", payment);

                            Map<String, Object> customerParams = new HashMap<>();
                            customerParams.put("name", payment.getCharges().getData().get(0).getBillingDetails().getName());
                            customerParams.put("email", email);
                            customerParams.put("description", "Customer for jenny.rosen@example.com");

                            Customer customer = Customer.create(customerParams);
                            LOGGER.info("customer {}", customer);

                            Map<String, Object> metadata = new HashMap<>();
                            metadata.put("customer", customer.getId());
                            metadata.put("receipt_email", email);
                            PaymentIntent paymentIntent = payment.update(metadata);

                            Map<String, Object> invoiceItemParams = new HashMap<String, Object>();
                            invoiceItemParams.put("customer", customer.getId());
                            invoiceItemParams.put("amount", 2500);
                            invoiceItemParams.put("currency", "usd");
                            invoiceItemParams.put("description", "One-time setup fee");

                            InvoiceItem.create(invoiceItemParams);

                            Map<String, Object> invoiceParams = new HashMap<String, Object>();
                            invoiceParams.put("customer", customer.getId());
                            invoiceParams.put("auto_advance", true); // auto-finalize this draft after ~1 hour
                            invoiceParams.put("collection_method", "send_invoice");
                            invoiceParams.put("days_until_due", 30);
//                            invoiceParams.put("default_source", customer.getDefaultSource());

                            Invoice invoice = Invoice.create(invoiceParams);
//                            invoice.pay();

                            invoice = Invoice.retrieve(invoice.getId());
                            invoice.finalizeInvoice();

                            invoice.sendInvoice();

                            Map<String, Object> receipt_email = new HashMap<>();
                            receipt_email.put("receipt_email", email);
                            assert payment != null;
                            payment = payment.update(receipt_email);

                        } catch (StripeException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            LOGGER.info("Numero de pasos {}", getStepNow());
            setStepNow(getStepNow() + 1);
        } else {
            //Analisis de la Informacion
            //TODO: implement logic
        } //end if else
    }

    @Override
    public void restartOperation(BoltonBot bot, Update update) throws TelegramApiException {
        //TODO: implement with a logic about sequence payment
    }
}
