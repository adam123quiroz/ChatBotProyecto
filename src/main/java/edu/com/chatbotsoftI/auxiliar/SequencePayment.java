package edu.com.chatbotsoftI.auxiliar;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.param.InvoiceUpdateParams;
import com.stripe.param.PaymentIntentUpdateParams;
import edu.com.chatbotsoftI.bl.SendEmailBl;
import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.commands.Command;
import edu.com.chatbotsoftI.dao.EvePaymentMethodRepository;
import edu.com.chatbotsoftI.dao.EvePaymentRepository;
import edu.com.chatbotsoftI.dao.EvePersonRepository;
import edu.com.chatbotsoftI.dao.EveTicketRepository;
import edu.com.chatbotsoftI.entity.EvePaymentEntity;

import edu.com.chatbotsoftI.entity.EvePaymentMethodEntity;
import edu.com.chatbotsoftI.entity.EvePersonEntity;
import edu.com.chatbotsoftI.entity.EveTicketEntity;
import edu.com.chatbotsoftI.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.AnswerPreCheckoutQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SequencePayment extends Sequence {
    private static final Logger LOGGER = LoggerFactory.getLogger(SequencePayment.class);
    private String email;
    private EvePaymentRepository evePaymentRepository;
    private SendEmailBl sendEmailBl;
    private EveTicketRepository eveTicketRepository;
    private EvePaymentMethodRepository evePaymentMethodRepository;
    private EvePersonRepository evePersonRepository;
    public SequencePayment(EvePaymentRepository evePaymentRepository, SendEmailBl sendEmailBl, EveTicketRepository eveTicketRepository
    ,EvePaymentMethodRepository evePaymentMethodRepository,EvePersonRepository evePersonRepository) {
        super(true, 2, 0);
        this.evePaymentRepository = evePaymentRepository;
        this.sendEmailBl = sendEmailBl;
        this.eveTicketRepository = eveTicketRepository;
        this.evePaymentMethodRepository = evePaymentMethodRepository;
        this.evePersonRepository = evePersonRepository;
    }

    @Override
    public void runSequence(Update update, BoltonBot bot) {
        if (getStepNow() < getNumberSteps()) {
            switch (getStepNow()) {
                case 0:
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
                            EvePaymentEntity evePaymentEntity = new EvePaymentEntity();
                            java.util.Date date;
                            date = new Date();
                            evePaymentEntity.setTxHost("Localhost");
                            evePaymentEntity.setTxUser("Admin");
                            evePaymentEntity.setTxDate(new java.sql.Date(new Date().getTime()));
                            evePaymentEntity.setDate(new java.sql.Date(date.getTime()));
                            evePaymentEntity.setStatus(Status.ACTIVE.getStatus());
                            evePaymentEntity.setTotal(new BigDecimal(payment.getAmount()));
                            String detalle = "Payment to bot @Bolton_EventBot";
                            int cantidad = 1;
                            long amount = payment.getAmount();
                            Date buydate = date;
                            InvoiceMaker invoiceMaker = new
                                    InvoiceMaker(buydate, detalle,cantidad, amount);
                            EvePaymentMethodEntity evePaymentMethodEntity = new EvePaymentMethodEntity();
                            evePaymentMethodEntity.setPaymentMethod(payment.getPaymentMethod());
                            evePaymentMethodRepository.save(evePaymentMethodEntity);

//                            evePaymentEntity.set
                            EvePersonEntity userEntity = evePersonRepository.findByBotUserId(update.getMessage().getFrom().getId().toString());
                            evePaymentEntity.setEvePersonByIdPerson(userEntity);
                            EveTicketEntity eveTicketEntity = new EveTicketEntity();
                            int x = (int) Math.random()*(1-(100000+1))+(10000);
                            String dato = String.valueOf(x);
                            eveTicketEntity.setNumberTicket(dato);
                            eveTicketEntity.setStatus(1);
                            eveTicketEntity.setTxDate(new java.sql.Date(new Date().getTime()));
                            eveTicketEntity.setTxHost("Localhost");
                            eveTicketEntity.setTxUser("admin");
                            eveTicketRepository.save(eveTicketEntity);
                            evePaymentEntity.setEveTicketByIdTicket(eveTicketEntity);
                            evePaymentEntity.setEvePaymentMethodByIdPaymentMethod(evePaymentMethodEntity);//

                            evePaymentEntity.setEvePaymentMethodByIdPaymentMethod(evePaymentMethodEntity);
                        evePaymentRepository.save(evePaymentEntity);
                        LOGGER.info("email {}", email);
                            sendEmailBl.sendMail("bolton@gmail.com", email, "Facturacion", "Hola", invoiceMaker);
                        } catch (StripeException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            LOGGER.info("Numero de pasos {}", getStepNow());
            setStepNow(getStepNow() + 1);
        }
//        else {
//            //Analisis de la Informacion
//        } //end if else
    }

    @Override
    public void restartOperation(BoltonBot bot, Update update)  {
        //TODO: implement with a logic about sequence payment
    }
}
