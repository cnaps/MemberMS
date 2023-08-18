package com.msa.rental.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Getter @Setter
public class ReturnItem {
    @Embedded
    private RentItem item;
    private LocalDate returnDate;

    public static ReturnItem sample(){
        return new ReturnItem(RentItem.sample(),LocalDate.now());
    }

    public static ReturnItem createRetunItem(RentItem rentItem){
        return new ReturnItem(rentItem,LocalDate.now());
    }
}
