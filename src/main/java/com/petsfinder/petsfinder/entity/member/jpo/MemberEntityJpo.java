package com.petsfinder.petsfinder.entity.member.jpo;

import com.petsfinder.petsfinder.entity.member.vo.MemberEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "MEMBER")
public class MemberEntityJpo {
    @Id
    @Column
    private String memberId;
    @Column
    private String memberAddress;
    @Column
    private String memberBirthday;
    @Column
    private String memberEmail;
    @Column
    private String memberGender;
    @Column
    private String memberName;
    @Column
    private String memberPass;
    @Column
    private String memberPhone;
    @Column
    private String memberPhoto;
    @Column
    private String memberType;

    public MemberEntityJpo() {
        //
    }

    public MemberEntityJpo(MemberEntity memberEntity) {
        this.memberId = memberEntity.getMemberId();
        this.memberAddress = memberEntity.getMemberAddress();
        this.memberBirthday = memberEntity.getMemberBirthday();
        this.memberEmail = memberEntity.getMemberEmail();
        this.memberGender = memberEntity.getMemberGender();
        this.memberName = memberEntity.getMemberName();
        this.memberPass = memberEntity.getMemberPass();
        this.memberPhone = memberEntity.getMemberPhone();
        this.memberPhoto = memberEntity.getMemberPhoto();
        this.memberType = memberEntity.getMemberType();
    }

    public MemberEntity toDomain() {
        return new MemberEntity(
            this.memberId,
            this.memberAddress,
            this.memberBirthday,
            this.memberEmail,
            this.memberGender,
            this.memberName,
            this.memberPass,
            this.memberPhone
        );
    }

    public static List<MemberEntity> toDomains(List<MemberEntityJpo> memberEntityJpos) {
        if (memberEntityJpos == null || memberEntityJpos.isEmpty())
            return new ArrayList<>();

        return memberEntityJpos.stream()
                .map(MemberEntityJpo::toDomain)
                .collect(Collectors.toList());
    }
}
