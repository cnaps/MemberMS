package com.msa.member.application.inputport;

import com.msa.member.application.outputport.MemberOutPutPort;
import com.msa.member.application.usecase.SavePointUsecase;
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
public class SavePointInputPort implements SavePointUsecase {

    private final MemberOutPutPort memberOutPutPort;
    @Override
    public MemberOutPutDTO savePoint(IDName idName, Long point) {
        Member loadMember = memberOutPutPort.loadMemberByIdName(idName);
        loadMember.savePoint(new Point(point));
        return MemberOutPutDTO.mapToDTO(loadMember);
    }
}
