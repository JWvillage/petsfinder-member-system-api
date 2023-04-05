package com.petsfinder.petsfinder.aggregate.member.logic;

import com.petsfinder.petsfinder.aggregate.member.service.MemberService;
import com.petsfinder.petsfinder.entity.member.jpo.MemberEntityJpo;
import com.petsfinder.petsfinder.entity.member.vo.MemberEntity;
import com.petsfinder.petsfinder.store.MemberStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberLogic implements MemberService {

    @Autowired
    MemberStore memberStore;

    @Override
    public List<MemberEntity> getMembers() {
        return MemberEntityJpo.toDomains(memberStore.getMembers());
    }

    @Override
    public MemberEntity retrieveId(String memberId) {
        return memberStore.retrieveId(memberId).toDomain();
    }

    @Override
    public MemberEntity retrieveName(String memberName) {
        return memberStore.retrieveName(memberName).toDomain();
    }

    @Override
    public MemberEntity idSearch(String memberName, String memberEmail) {
        return memberStore.idSearch(memberName, memberEmail).toDomain();
    }

    @Override
    public MemberEntity pwSearch(String memberId, String memberName, String memberEmail) {
        return memberStore.pwSearch(memberId, memberName, memberEmail).toDomain();
    }

    @Override
    public void registration(MemberEntity memberEntity) {
        memberStore.registration(new MemberEntityJpo(memberEntity));
    }

    @Override
    public MemberEntity login(String memberId, String memberPass) {
        return memberStore.login(memberId, memberPass).toDomain();
    }
}
