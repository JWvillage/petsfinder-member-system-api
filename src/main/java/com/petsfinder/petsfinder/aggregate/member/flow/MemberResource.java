package com.petsfinder.petsfinder.aggregate.member.flow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petsfinder.petsfinder.aggregate.helper.util.Util;
import com.petsfinder.petsfinder.aggregate.member.service.MemberService;
import com.petsfinder.petsfinder.entity.member.vo.MemberEntity;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MemberResource {

    @Autowired
    MemberService memberService;

    @RequestMapping(value = "/v1/apis/PETSFINDER/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> login(@RequestBody String member) throws ParseException {

        JSONObject jo = Util.StringToJSONObject(member);

        MemberEntity loginMember = memberService.login(jo.get("memberId").toString(), jo.get("memberPass").toString());

        Map<String, String> model = new HashMap<>();

        try {
            if (loginMember != null && loginMember.getMemberId() != null) {
                model.put("member", new ObjectMapper().writeValueAsString(loginMember));
                model.put("status", "200");
            } else {
                model.put("errorMsg", "존재하지 않는 아이디 또는 비밀번호입니다!");
                model.put("status", "600");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return model;
    }
    @RequestMapping(value = "/v1/apis/PETSFINDER/registration", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> registration(@RequestBody String member) {

        Map<String, String> model = new HashMap<>();

        try {

            JSONObject jo = Util.StringToJSONObject(member);

            Map<String, String> map = new HashMap<>();

            jo.keySet().forEach(key -> {
                String value = jo.get(key).toString();
                map.put(key.toString(), value);
                map.put("memberType", "normal");
            });

            MemberEntity registMember = new ObjectMapper().convertValue(map, MemberEntity.class);

            MemberEntity retrieveMember = memberService.retrieveName(registMember.getMemberName());

            if (retrieveMember == null || retrieveMember.getMemberId() == null) {
                memberService.registration(registMember);
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

    @RequestMapping(value = "/v1/apis/PETSFINDER/idSearch", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> idSearch(@RequestBody String member) {

        Map<String, String> model = new HashMap<>();

        try {
            JSONObject jo = Util.StringToJSONObject(member);

            MemberEntity findMember = memberService.idSearch(jo.get("memberName").toString(), jo.get("memberEmail").toString());

            if (findMember != null && findMember.getMemberId() != null) {
                model.put("member", new ObjectMapper().writeValueAsString(findMember));
                model.put("status", "200");
            } else {
                model.put("errorMsg", "이름 또는 이메일 정보가 잘못되었습니다! 확인 후 다시 이용해주세요!");
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

    @RequestMapping(value = "/v1/apis/PETSFINDER/pwSearch", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> pwSearch(@RequestBody String member) {

        Map<String, String> model = new HashMap<>();

        try {
            JSONObject jo = Util.StringToJSONObject(member);

            MemberEntity findMember = memberService.pwSearch(jo.get("memberId").toString(), jo.get("memberName").toString(), jo.get("memberEmail").toString());

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

    @RequestMapping(value = "/v1/apis/PETSFINDER/idCheck", method = RequestMethod.POST)
    @ResponseBody
    public String idCheck(@RequestBody String memberId) {

        String message = "";

        MemberEntity retrieveMember = memberService.retrieveId(memberId);

        if (retrieveMember == null || retrieveMember.getMemberId() == null) {
            message = "possible";
        } else {
            message = "impossible";
        }

        return message;
    }
}
