package com.petsfinder.petsfinder.store.jpastore;

import com.petsfinder.petsfinder.entity.member.jpo.MemberEntityJpo;
import com.petsfinder.petsfinder.store.MemberStore;
import com.petsfinder.petsfinder.store.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberJpaStore implements MemberStore {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public List<MemberEntityJpo> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    public MemberEntityJpo retrieveId(String memberId) {
        return memberRepository.findByMemberId(memberId) == null
                ? new MemberEntityJpo() : memberRepository.findByMemberId(memberId);
    }

    @Override
    public MemberEntityJpo retrieveName(String memberName) {
        return memberRepository.findByMemberName(memberName) == null
                ? new MemberEntityJpo() : memberRepository.findByMemberName(memberName);
    }

    @Override
    public MemberEntityJpo idSearch(String memberName, String memberEmail) {
        return memberRepository.findByMemberNameAndMemberEmail(memberName, memberEmail) == null
                ? new MemberEntityJpo() : memberRepository.findByMemberNameAndMemberEmail(memberName, memberEmail);
    }

    @Override
    public MemberEntityJpo pwSearch(String memberId, String memberName, String memberEmail) {
        return memberRepository.findByMemberIdAndMemberNameAndMemberEmail(memberId, memberName, memberEmail) == null
                ? new MemberEntityJpo() : memberRepository.findByMemberIdAndMemberNameAndMemberEmail(memberId, memberName, memberEmail);
    }

    @Override
    public void registration(MemberEntityJpo memberEntityJpo) {
        memberRepository.save(memberEntityJpo);
    }

    @Override
    public MemberEntityJpo login(String memberId, String memberPass) {
        return memberRepository.findByMemberIdAndMemberPass(memberId, memberPass) == null
                ? new MemberEntityJpo() : memberRepository.findByMemberIdAndMemberPass(memberId, memberPass);
    }
}