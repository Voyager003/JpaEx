package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    @DisplayName("회원가입")
    public void MemberServiceTest1() throws Exception {

        //given
        Member member = new Member();
        member.setName("rim");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));
     }

     @Test
     @DisplayName("중복회원예외")
     public void MemberServiceTest2() throws Exception {

        // given
        Member member1 = new Member();
        member1.setName("rim1");

        Member member2 = new Member();
        member2.setName("rim1");


        // when
        memberService.join(member1);

        Assertions.assertThrows(IllegalStateException.class, ()->
                memberService.join(member2)
                );

        //then

      }
}