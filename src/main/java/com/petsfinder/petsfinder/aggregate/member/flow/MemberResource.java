package com.petsfinder.petsfinder.aggregate.member.flow;

import com.petsfinder.petsfinder.aggregate.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MemberResource {

    @Autowired
    MemberService memberService;

    @RequestMapping(value = "/v1/apis/PETSFINDER/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> login(@RequestBody String member) {
        
        return memberService.login(member);
    }
    @RequestMapping(value = "/v1/apis/PETSFINDER/registration", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> registration(@RequestBody String member) {

        return memberService.registration(member);
    }

    @RequestMapping(value = "/v1/apis/PETSFINDER/idSearch", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> idSearch(@RequestBody String member) {
    
        return memberService.idOrPasswordSearch(member, "id");
    }

    @RequestMapping(value = "/v1/apis/PETSFINDER/pwSearch", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> pwSearch(@RequestBody String member) {
        
        return memberService.idOrPasswordSearch(member, "password");
    }

    @RequestMapping(value = "/v1/apis/PETSFINDER/idCheck", method = RequestMethod.POST)
    @ResponseBody
    public String idCheck(@RequestBody String memberId) {

        return memberService.idCheck(memberId);
    }
}
