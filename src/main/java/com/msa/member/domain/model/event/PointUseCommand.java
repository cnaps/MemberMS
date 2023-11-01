package com.msa.rental.domain.model.event;

import com.msa.rental.domain.model.vo.IDName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PointUseCommand {
    private IDName idName;
    private long point;
}
