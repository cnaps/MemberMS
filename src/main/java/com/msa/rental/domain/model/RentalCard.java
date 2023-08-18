package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Getter @Setter
@Data
@Entity
@NoArgsConstructor
public class RentalCard {

    @EmbeddedId
    private RentalCardNo rentalCardId;
    @Embedded
    private IDName member;
    private RentStatus rentStatus;
    @Embedded
    private LateFee totalLateFee;

    @ElementCollection
    private List<RentItem> rentItemList = new ArrayList<RentItem>();
    @ElementCollection
    private List<ReturnItem> returnItemList = new ArrayList<ReturnItem>();

    public static RentalCard createRentalCard(IDName creater)
    {
        RentalCard rentalCard = new RentalCard();
        rentalCard.setRentalCardId(RentalCardNo.createRentalCardNo());
        rentalCard.setMember(creater);
        rentalCard.setRentStatus(RentStatus.RENT_AVAILABLE);
        rentalCard.setTotalLateFee(LateFee.createLateFee());
        return rentalCard; 
    }

    public RentalCard rentItem(Item item) throws Exception {
        checkRentalAvailable();
        this.addRentalItem(RentItem.createRentalItem(item));
        return this;
    }

    public RentalCard returnItem(Item item,LocalDate returnDate) throws Exception {
        RentItem rentedItem = this.rentItemList.stream().filter(i -> i.getItem().equals(item)).findFirst().get();
        calculateLateFee(rentedItem,returnDate);
        this.addReturnItem(ReturnItem.createRetunItem(rentedItem));
        this.removeRentalItem(rentedItem);
        return this;
    }

    public RentalCard overdueItem(Item item){
        RentItem rentedItem = this.rentItemList.stream().filter(i -> i.getItem().equals(item)).findFirst().get();
        rentedItem.setOverdued(true);
        //rentedItem.setOverdueDate(LocalDate.of(2022,10,20));
        rentedItem.setOverdueDate(LocalDate.now().minusDays(1));
        //this.totalLateFee.addPoint(10);
        this.rentStatus = RentStatus.RENT_UNAVAILABLE;
        return this;
    }

    private void checkRentalAvailable() throws Exception {
        if (this.rentStatus == RentStatus.RENT_UNAVAILABLE) throw new IllegalStateException("대출 불가 상태입니다.");
        if ((long) this.rentItemList.size() > 5) throw new Exception("이미 5권 대출하였습니다.");
    }

    private RentalCard calculateLateFee(RentItem item, LocalDate returnDate){
        if (returnDate.compareTo(item.getOverdueDate())>0) {
            Integer point = 0;
            point += Period.between(item.getOverdueDate(), returnDate).getDays() * 10;
            LateFee lateFee = this.totalLateFee.addPoint(point);
            this.setTotalLateFee(lateFee);
        }
        return this;
    }
    public RentalCard calculateLateFee(){
        this.rentItemList.forEach(new Consumer<RentItem>() {
                                        @Override
                                        public void accept(RentItem rentItem) {
                                           if(rentItem.getOverdueDate() != null)
                                           {
                                              calculateLateFee(rentItem,LocalDate.now());
                                           }
                                        }
                                    }

        );
        return this;
    }

    public RentalCard makeAvailableRental(Integer point) throws Exception {
        // 연체비 계산하기
        if ((long) this.rentItemList.size() != 0) throw new IllegalStateException("모든 도서가 반납된 후 정지를 해제할 수 있습니다.");
        Integer lateFee = this.totalLateFee.getPoint();
        if (lateFee > point) throw new IllegalStateException("연체료가 더 커서 해당 포인트로 삭감할수 없습니다.");
        lateFee = lateFee - point;
        LateFee total = this.totalLateFee.addPoint(lateFee);

        if (lateFee == 0 )
        {
            this.rentStatus = RentStatus.RENT_AVAILABLE;
        }
        this.setTotalLateFee(total);
        return this;
    }


    public void addRentalItem(RentItem rentItem){
        this.getRentItemList().add(rentItem);
    }

    public void addReturnItem(ReturnItem returnItem){
        this.getReturnItemList().add(returnItem);
    }

    public void removeRentalItem(RentItem rentItem)
    {
        this.getRentItemList().remove(rentItem);
    }
    public static RentalCard sample(){
        RentalCard rentalCard = new RentalCard();
        rentalCard.setRentalCardId(RentalCardNo.createRentalCardNo());
        rentalCard.setMember(IDName.sample());
        rentalCard.setRentStatus(RentStatus.RENT_AVAILABLE);
        rentalCard.setTotalLateFee(LateFee.sample());
        //rentalCard.getRentalItemList().add(RentalItem.sample());

        return rentalCard;
    }
}
