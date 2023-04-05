package com.petsfinder.petsfinder.store;

import com.petsfinder.petsfinder.entity.member.jpo.MemberEntityJpo;

import java.util.List;

public interface MemberStore {

    List<MemberEntityJpo> getMembers();

    MemberEntityJpo retrieveId(String memberId);

    MemberEntityJpo retrieveName(String memberName);

    MemberEntityJpo idSearch(String memberName, String memberEmail);

    MemberEntityJpo pwSearch(String memberId, String memberName, String memberEmail);

    void registration(MemberEntityJpo memberEntityJpo);

    MemberEntityJpo login(String memberId, String memberPass);
}
