package com.project.authorization_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "member")
public class MemberVo {
    @Id
    @Column(name = "member_id")
    private String memberId;
    private String userId;
    private String userPw;
    private String name;
    private String email;
    private String phone;
    private String nickname;
    private String registerTime;
    @Column(name = "recent_login_time")
    private String recentLoginTime;
    private String role;

    public MemberVo(String memberId) {
        this.memberId = memberId;
    }

    @Builder
    public MemberVo(String memberId, String userId, String userPw, String name, String email, String phone, String nickname, String registerTime, String recentLoginTime, String role) {
        this.memberId = memberId;
        this.userId = userId;
        this.userPw = userPw;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.nickname = nickname;
        this.registerTime = registerTime;
        this.recentLoginTime = recentLoginTime;
        this.role = role;
    }
}
