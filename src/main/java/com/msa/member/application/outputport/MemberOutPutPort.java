package com.msa.member.application.outputport;

import com.msa.member.domain.model.Member;
import com.msa.member.domain.model.vo.IDName;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberOutPutPort {
    public Member loadMember(long memberNo);
    public  Member loadMemberByIdName(IDName idName);
    public Member saveMember(Member member);
}
