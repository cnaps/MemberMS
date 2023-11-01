package com.msa.member.domain.model.event;

import com.msa.member.domain.model.vo.IDName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventResult  implements Serializable {
    private EventType eventType;
    private boolean isSuccessed;
    private IDName idName;
    private Item item;
    private long point;
}