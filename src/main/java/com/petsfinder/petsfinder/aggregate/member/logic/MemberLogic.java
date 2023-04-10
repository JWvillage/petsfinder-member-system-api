package com.petsfinder.petsfinder.aggregate.member.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petsfinder.petsfinder.aggregate.helper.util.Util;
import com.petsfinder.petsfinder.aggregate.member.service.MemberService;
import com.petsfinder.petsfinder.entity.member.jpo.MemberEntityJpo;
import com.petsfinder.petsfinder.entity.member.vo.MemberEntity;
import com.petsfinder.petsfinder.store.MemberStore;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberLogic implements MemberService {

    @Autowired
    MemberStore memberStore;

    @Override
    public List<MemberEntity> getMembers() {
        return MemberEntityJpo.toDomains(memberStore.getMembers());
    }

    @Override
    public String idCheck(String memberId) {
        
        String message;
    
        MemberEntity retrieveMember = memberStore.retrieveId(memberId).toDomain();
    
        if (retrieveMember == null || retrieveMember.getMemberId() == null) {
            message = "possible";
        } else {
            message = "impossible";
        }
    
        return message;
    }

    @Override
    public Map<String, String> idOrPasswordSearch(String requestMemberData, String searchField) {
    
        Map<String, String> model = new HashMap<>();
    
        try {
            JSONObject jo = Util.StringToJSONObject(requestMemberData);
    
            MemberEntity findMember = new MemberEntity();
            if (searchField.equals("id"))
                findMember = memberStore.idSearch(jo.get("memberName").toString(), jo.get("memberEmail").toString()).toDomain();
            else if (searchField.equals("password"))
                findMember = memberStore.pwSearch(jo.get("memberId").toString(), jo.get("memberName").toString(), jo.get("memberEmail").toString()).toDomain();
                
            if (findMember != null && findMember.getMemberId() != null) {
                model.put("member", new ObjectMapper().writeValueAsString(findMember));
                model.put("status", "200");
            } else {
                model.put("errorMsg", "기입 정보가 잘못되었습니다! 확인 후 다시 이용해주세요!");
                model.put("status", "300");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            model.put("errorMsg", "정보가 제대로 입력되지 않았습니다! 확인 후 다시 이용 해주세요!");
            model.put("status", "600");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    
        return model;
    }
    
    @Override
    public Map<String, String> registration(String requestMember) {
    
        Map<String, String> model = new HashMap<>();
    
        try {
            MemberEntity registerMember = Util.MapToEntity(Util.StringToJSONObject(requestMember), Map.of("memberType", "normal"), MemberEntity.class);
        
            MemberEntity retrieveMember = memberStore.retrieveName(registerMember.getMemberName()).toDomain();
        
            if (retrieveMember == null || retrieveMember.getMemberId() == null) {
                memberStore.registration(new MemberEntityJpo(registerMember));
                model.put("status", "200");
            } else {
                model.put("errorMsg", "이미 가입한 적 있는 회원입니다! 아이디 찾기로 이동합니다!");
                model.put("status", "300");
            }
        
        } catch (ParseException e) {
            e.printStackTrace();
            model.put("errorMsg", "가입 정보가 제대로 입력되지 않았습니다! 확인 후 다시 이용 해주세요!");
            model.put("status", "600");
        }
    
        return model;
    }

    @Override
    public Map<String, String> login(String requestMember) {
        
        Map<String, String> model = new HashMap<>();
        
        try {
            JSONObject jo = Util.StringToJSONObject(requestMember);
        
            MemberEntity loginMember = memberStore.login(jo.get("memberId").toString(), jo.get("memberPass").toString()).toDomain();
            
            if (loginMember != null && loginMember.getMemberId() != null) {
                model.put("member", new ObjectMapper().writeValueAsString(loginMember));
                model.put("status", "200");
            } else {
                model.put("errorMsg", "존재하지 않는 아이디 또는 비밀번호입니다!");
                model.put("status", "300");
            }
        } catch (ParseException e) {
            model.put("errorMsg", "알 수 없는 API 오류입니다!");
            model.put("status", "600");
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        
        return model;
    }
}
