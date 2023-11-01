package com.msa.member.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Authority {
    public UserRole roleName;

    public static Authority sample()
    {
        return new Authority(UserRole.USER);
    }
}
