package com.msa.member.domain.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Item {
    private Integer no;
    private String title;

    public static Item Sample(){
        return new Item(10,"노인과바다");
    }
}
