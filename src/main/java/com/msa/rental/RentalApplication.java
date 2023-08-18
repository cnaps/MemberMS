package com.msa.rental;

import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.Item;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.stream.Collectors;

@SpringBootApplication
public class RentalApplication {

    public static void main(String[] args) throws Exception {
        domainTest();
        SpringApplication.run(RentalApplication.class, args);
    }

    public static void domainTest() throws Exception {

        System.out.println("------------도메인 모델 테스트 진행------------");
        System.out.println("도서카드 생성");
        RentalCard sampleCard = RentalCard.sample();
        System.out.println("도서카드명 " + sampleCard.getMember().getName());
        System.out.println("-----------------------------------------");
    
        var sample1 = new Item(1,"노인과바다");
        var smaple2 = new Item(2, "홍길동전");
        var sample3 = new Item(3,"누구를 위하여 종을 울리나?");

        System.out.println("도서명: " + sample1.getTitle() + " 대여됨");
        sampleCard.rentItem(sample1);
        System.out.println("도서명: " + smaple2.getTitle() + " 대여됨");
        sampleCard.rentItem(smaple2);
        System.out.println("도서명: " + sample3.getTitle() + " 대여됨");
        sampleCard.rentItem(sample3);
        System.out.println("도서명: " + Item.sample().getTitle() + " 대여됨");
        sampleCard.rentItem(Item.sample());

        System.out.println("-----------------------------------------");
        System.out.println(sampleCard.getMember().getName() + " 대여 목록");
        System.out.println(sampleCard.getRentItemList().stream().map(m->m.getItem().getTitle()).collect(Collectors.toList()));
        System.out.println(sampleCard.getMember().getName() + " 반납목록");
        System.out.println(sampleCard.getReturnItemList().stream().map(m->m.getItem().getItem().getTitle()).collect(Collectors.toList()));
        System.out.println("-----------------------------------------");
    
        System.out.println("도서명: " + Item.sample().getTitle()+" 반납됨");
        sampleCard.returnItem(Item.sample(), LocalDate.now());
        
        System.out.println(sampleCard.getMember().getName() + " 대여 목록");
        System.out.println(sampleCard.getRentItemList().stream().map(m->m.getItem().getTitle()).collect(Collectors.toList()));
        System.out.println(sampleCard.getMember().getName() + " 반납 목록");
        System.out.println(sampleCard.getReturnItemList().stream().map(m->m.getItem().getItem().getTitle()).collect(Collectors.toList()));
        System.out.println("-----------------------------------------");
    
        System.out.println("도서명: " + Item.sample2().getTitle() + " 대여됨");
        sampleCard.rentItem(Item.sample2());

        System.out.println(sampleCard.getMember().getName() + " 대여 목록");
        System.out.println(sampleCard.getRentItemList().stream().map(m->m.getItem().getTitle()).collect(Collectors.toList()));
        System.out.println(sampleCard.getMember().getName() + " 반납 목록");
        System.out.println(sampleCard.getReturnItemList().stream().map(m->m.getItem().getItem().getTitle()).collect(Collectors.toList()));
        System.out.println("-----------------------------------------");
    
        System.out.println("도서명: " + Item.sample2().getTitle() + " 강제 연체");
        sampleCard.overdueItem(Item.sample2());
        System.out.println("도서명: " + sample3.getTitle() + " 강제 연체");
        sampleCard.overdueItem(sample3);

        System.out.println(sampleCard.getMember().getName() + " 대여 목록");
        System.out.println(sampleCard.getRentItemList().stream().map(m->m.getItem().getTitle()).collect(Collectors.toList()));
        System.out.println(sampleCard.getMember().getName() + " 반납 목록");
        System.out.println(sampleCard.getReturnItemList().stream().map(m->m.getItem().getItem().getTitle()).collect(Collectors.toList()));
       
        System.out.println("-----------------------------------------");
    
        System.out.println("도서카드 " + sampleCard.getMember().getName()+ "------현재 상태-----");
        System.out.println("대여도서 연체상태 : " + sampleCard.getRentItemList().stream().map(m->m.isOverdued()).collect(Collectors.toList()));
        System.out.println("총연체료: " + sampleCard.getTotalLateFee().getPoint());
        System.out.println("대여가능여부: " + sampleCard.getRentStatus().toString());

        sampleCard.calculateLateFee();

        System.out.println("총연체료: " + sampleCard.getTotalLateFee().getPoint());
        System.out.println(sampleCard.getRentItemList().stream().map(m->m.getItem().getTitle()).collect(Collectors.toList()));
        System.out.println(sampleCard.getReturnItemList().stream().map(m->m.getItem().getItem().getTitle()).collect(Collectors.toList()));

    }
}
