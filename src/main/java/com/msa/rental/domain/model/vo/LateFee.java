package com.msa.rental.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Getter
public class LateFee {
    private Integer point;

    public LateFee addPoint(Integer lateFee)
    {
        return new LateFee(this.point + lateFee);
    }

    public LateFee removePoint(Integer lateFee) throws Exception {
        if (lateFee > this.point) {
            throw new Exception("기존 가진 point보다 작어 삭제할 수 없습니다.");
        }
        return new LateFee(this.point - lateFee);
    }
    public static LateFee sample(){
        return new LateFee(0);
    }

    public static LateFee createLateFee()
    {
        LateFee lateFee = new LateFee();
        lateFee.point = 0;
        return lateFee;
    }
}
