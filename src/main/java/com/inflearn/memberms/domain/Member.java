package com.inflearn.memberms.domain;

import com.inflearn.memberms.domain.vo.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Data
public class Member {


    private Long id;

    private IDName idName;

    private Password password;

    private Email email;

    private List<Authority> authorityList;

    private Point point;

    public Member registerMember(){

        return this;
    }

    public void editMember(){
    }

    public void deleteMember(){}

    public Member login(IDName iDname,Password password)
    {
        return this;
    }

    public void logout(IDName iDname){}

    public Member addAuthority(Authority authority){
        return this;
    }

    public Member getSample(){
        return this;
    }

}
