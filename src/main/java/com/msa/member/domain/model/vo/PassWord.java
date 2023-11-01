package com.msa.member.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PassWord {
    public String presentPWD;
    public String pastPWD;

    public static PassWord sample(){
        return new PassWord("12345","abcde");
    }
}
