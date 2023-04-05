package com.petsfinder.petsfinder.store.repository;

import com.petsfinder.petsfinder.entity.member.jpo.MemberEntityJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntityJpo, String> {

    // retrieveId
    MemberEntityJpo findByMemberId(String memberId);

    // retrieveName
    MemberEntityJpo findByMemberName(String memberName);

    // login
    MemberEntityJpo findByMemberIdAndMemberPass(String memberId, String memberPass);

    // id search
    MemberEntityJpo findByMemberNameAndMemberEmail(String memberName, String memberEmail);

    // pw search
    MemberEntityJpo findByMemberIdAndMemberNameAndMemberEmail(String memberId, String memberName, String memberEmail);
}
