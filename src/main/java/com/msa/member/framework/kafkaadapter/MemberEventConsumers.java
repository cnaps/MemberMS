package com.msa.member.framework.kafkaadapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.member.application.usecase.SavePointUsecase;
import com.msa.member.application.usecase.UsePointUsecase;
import com.msa.member.domain.model.event.*;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MemberEventConsumers {

    private final Logger log = LoggerFactory.getLogger(MemberEventConsumers.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SavePointUsecase savePointUsecase;
    private final UsePointUsecase usePointUsecase;
    private final MemberEventProducer eventProducer;

    @KafkaListener(topics="${consumer.topic1.name}",groupId = "${consumer.groupid.name}")
    public void consumeRent(ConsumerRecord<String, String> record) throws IOException {
        System.out.printf("rental_rent:"+ record.value());
        ItemRented itemRented = objectMapper.readValue(record.value(), ItemRented.class);
        savePointUsecase.savePoint(itemRented.getIdName(),itemRented.getPoint());
    }
    @KafkaListener(topics="${consumer.topic2.name}",groupId = "${consumer.groupid.name}")
    public void consumeReturn(ConsumerRecord<String, String> record) throws IOException{
        System.out.printf("rental_return:"+ record.value());
        ItemReturned itemReturned = objectMapper.readValue(record.value(), ItemReturned.class);
        savePointUsecase.savePoint(itemReturned.getIdName(),itemReturned.getPoint());
    }
    @KafkaListener(topics="${consumer.topic3.name}",groupId = "${consumer.groupid.name}")
    public void consumeClear(ConsumerRecord<String, String> record) throws Exception {
        OverdueCleared overdueCleared = objectMapper.readValue(record.value(), OverdueCleared.class);

        EventResult eventResult = new EventResult();
        eventResult.setEventType(EventType.OVERDUE);
        eventResult.setIdName(overdueCleared.getIdName());
        eventResult.setPoint(overdueCleared.getPoint());

        System.out.printf(record.value());
        try
        {
            usePointUsecase.userPoint(overdueCleared.getIdName(),overdueCleared.getPoint());
            eventResult.setSuccessed(true);
        }
        catch (Exception e)
        {
            eventResult.setSuccessed(false);
        }
        eventProducer.occurEvent(eventResult);
    }

    @KafkaListener(topics="${consumer.topic4.name}",groupId = "${consumer.groupid.name}")
    public void consumeUsePoint(ConsumerRecord<String, String> record) throws Exception {
        System.out.printf(record.value());
        PointUseCommand pointUseCommand = objectMapper.readValue(record.value(), PointUseCommand.class);
        try {
            usePointUsecase.userPoint(pointUseCommand.getIdName(), pointUseCommand.getPoint());
        } catch (Exception e) {
            throw e;
        }

    }
}
