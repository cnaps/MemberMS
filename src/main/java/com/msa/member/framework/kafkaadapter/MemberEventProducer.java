package com.msa.member.framework.kafkaadapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.msa.member.domain.model.event.EventResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
public class MemberEventProducer {

    @Value(value = "${producers.topic1.name}")
    private  String TOPIC;
    private final KafkaTemplate<String, EventResult> kafkaTemplate;


    public void occurEvent(EventResult result) throws JsonProcessingException {
        ListenableFuture<SendResult<String, EventResult>> future = this.kafkaTemplate.send(TOPIC, result);

        future.addCallback(new ListenableFutureCallback<SendResult<String, EventResult>>() {
            private final Logger LOGGER = LoggerFactory.getLogger(MemberEventProducer.class);

            @Override
            public void onSuccess(SendResult<String, EventResult> result) {
                EventResult g = result.getProducerRecord().value();
                LOGGER.info("Sent message=[" + g.getEventType() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable t) {
                // needed to do compensation transaction.
                LOGGER.error( "Unable to send message=[" + result.getEventType() + "] due to : " + t.getMessage(), t);
            }
        });
    }
}