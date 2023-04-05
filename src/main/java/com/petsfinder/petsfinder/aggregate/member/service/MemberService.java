package com.petsfinder.petsfinder.aggregate.member.service;

import com.petsfinder.petsfinder.entity.member.vo.MemberEntity;

import java.util.List;

public interface MemberService {

    List<MemberEntity> getMembers();

    MemberEntity retrieveId(String memberId);

    MemberEntity retrieveName(String memberName);

    MemberEntity idSearch(String memberName, String memberEmail);

    MemberEntity pwSearch(String memberId, String memberName, String memberEmail);

    void registration(MemberEntity memberEntity);

    MemberEntity login(String memberId, String memberPass);
}
