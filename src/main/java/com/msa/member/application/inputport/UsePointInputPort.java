package com.msa.member.application.inputport;

import com.msa.member.application.outputport.MemberOutPutPort;
import com.msa.member.application.usecase.UsePointUsecase;
import com.msa.member.domain.model.Member;
import com.msa.member.domain.model.vo.IDName;
import com.msa.member.domain.model.vo.Point;
import com.msa.member.framework.web.dto.MemberOutPutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UsePointInputPort implements UsePointUsecase {

    private final MemberOutPutPort memberOutPutPort;
    @Override
    public MemberOutPutDTO userPoint(IDName idName, long point) throws Exception {
        Member loadMember = memberOutPutPort.loadMemberByIdName(idName);
        loadMember.usePoint(new Point(point));
        return MemberOutPutDTO.mapToDTO(loadMember);
    }
}
