package com.msa.member.domain.model.event;


import com.msa.member.domain.model.vo.IDName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PointUseCommand implements Serializable {
    private IDName idName;
    private long point;
}
