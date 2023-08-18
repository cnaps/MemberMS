package com.msa.rental.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDate;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RentItem {

    @Embedded
    private Item item;
    private LocalDate rentDate;
    private boolean overdued;
    //반납 예정일
    private LocalDate overdueDate;

    public static RentItem createRentalItem(Item item)
    {
        return new RentItem(item,LocalDate.now(),false,LocalDate.now().plusDays(14));
    }

    public static RentItem sample(){
        return new RentItem(Item.sample(),LocalDate.now(),false,LocalDate.now().plusDays(14));
    }
}
