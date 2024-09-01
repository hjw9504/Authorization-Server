package com.project.authorization_server.repository;

import com.project.authorization_server.entity.MemberVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberVo, Long> {
    List<MemberVo> findUserByMemberId(String memberId);

    MemberVo findUserByUserId(String userId);

    @Query("select m from member m where 1=1")
    List<MemberVo> findAllUser();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update member m set m.recentLoginTime = :recentLoginTime where m.memberId = :memberId")
    int updateLastLoginTime(String memberId, String recentLoginTime);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update member m set m.userPw = :userPw where m.userId = :userId")
    int resetUserPassword(String userId, String userPw);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update member m set m.nickname = :nickname where m.memberId = :memberId")
    int updateNickName(String memberId, String nickname);
}
