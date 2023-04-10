package com.petsfinder.petsfinder.aggregate.member.service;

import com.petsfinder.petsfinder.entity.member.vo.MemberEntity;

import java.util.List;
import java.util.Map;

public interface MemberService {

    // Admin에서 사용할 Service
    List<MemberEntity> getMembers();

    String idCheck(String memberId);

    Map<String, String> idOrPasswordSearch(String requestMemberData, String searchField);
    
    Map<String, String> registration(String requestMember);

    Map<String, String> login(String requestMember);
}
