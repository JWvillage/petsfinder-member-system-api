package com.petsfinder.petsfinder.entity.member.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberEntity {
    private String memberId;
    private String memberAddress;
    private String memberBirthday;
    private String memberEmail;
    private String memberGender;
    private String memberName;
    private String memberPass;
    private String memberPhone;
    private String memberPhoto;
    private String memberType;

    public MemberEntity() {
        //
    }

    public MemberEntity(
        String memberId,
        String memberAddress,
        String memberBirthday,
        String memberEmail,
        String memberGender,
        String memberName,
        String memberPass,
        String memberPhone
    ) {
        this.memberId = memberId;
        this.memberAddress = memberAddress;
        this.memberBirthday = memberBirthday;
        this.memberEmail = memberEmail;
        this.memberGender = memberGender;
        this.memberName = memberName;
        this.memberPass = memberPass;
        this.memberPhone = memberPhone;
        this.memberPhoto = "";
        this.memberType = "normal";
    }
}
