package org.loopingdoge.acme.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.loopingdoge.acme.jolie.sessionmanager.ACMESessionServer;
import org.loopingdoge.acme.jolie.sessionmanager.ACMESessionServerService;
import org.loopingdoge.acme.model.House;
import org.loopingdoge.acme.utils.AcmeExternalServices;
import org.loopingdoge.acme.utils.AcmeVariables;
import org.loopingdoge.acme.utils.AcmeWaitStateNames;
import org.loopingdoge.acme.utils.MailServiceAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.logging.Logger;

public class SendBuyerMeetingProposal implements JavaDelegate {

    private final static Logger logger = Logger.getLogger("SendBuyerMeetingProposal");

    private final String BASE_URL = AcmeExternalServices.MAIL;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private MailServiceAPI mailService = retrofit.create(MailServiceAPI.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        logger.info("service started");

        // Remove seller session info
        ACMESessionServer sessionWs = new ACMESessionServerService().getACMESessionServerServicePort();
        sessionWs.removeSession(execution.getProcessInstanceId());

        House acceptedHouse = (House) execution.getVariable(AcmeVariables.CHOSEN_HOUSE);

        // Send mail notification to buyer
        mailService.send(
                (String) execution.getVariable(AcmeVariables.BUYER_NAME),
                "AcmeAgency",
                "Il proprietario di " + acceptedHouse.getName() + " ha specificato date di disponibilita'")
                .execute().body();

        // Add buyer session
        sessionWs.addSession(
                (String) execution.getVariable(AcmeVariables.BUYER_NAME),
                execution.getProcessInstanceId(),
                AcmeWaitStateNames.WAIT_BUYER_MEETING_RESPONSE);
    }

}
