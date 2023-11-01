package com.msa.member.application.usecase;

import com.msa.member.framework.web.dto.MemberOutPutDTO;

public interface InquiryMemberUsecase {
    public MemberOutPutDTO getMember(long memberNo);
}
