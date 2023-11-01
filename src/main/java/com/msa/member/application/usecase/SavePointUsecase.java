package com.msa.member.application.usecase;

import com.msa.member.domain.model.vo.IDName;
import com.msa.member.framework.web.dto.MemberOutPutDTO;

public interface SavePointUsecase {
    public MemberOutPutDTO savePoint(IDName idName, Long point);
}
