package com.engagepoint.university.messaging.jms;

import com.engagepoint.university.messaging.dto.JmsDTO;
import com.engagepoint.university.messaging.service.repository.JmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.Date;

@MessageDriven(mappedName = "jms/myQueue")
public class JMSConsumer implements MessageListener {
    private static final Logger LOG = LoggerFactory.getLogger(JMSConsumer.class);

    @Inject
    JmsService jmsService;

    @Override
    public void onMessage(Message message) {
        String msg;
        try {
            msg = ((TextMessage) message).getText();
            JmsDTO jmsDTO = new JmsDTO();
            jmsDTO.setMsg(msg.getBytes());
            jmsService.save(jmsDTO);
        } catch (JMSException e) {
            LOG.warn("Error while fetching message:" + e.getMessage(), e);
        }
    }
}
