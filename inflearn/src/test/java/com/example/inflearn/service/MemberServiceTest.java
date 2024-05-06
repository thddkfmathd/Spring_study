package com.example.inflearn.service;

import com.example.inflearn.domain.Member;
import com.example.inflearn.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//원하는 클래스에서 ctrl shift t
class MemberServiceTest {
    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    public  void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void join회원가입() {
        //given
        Member member = new Member();
        member.setName("hello222");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    void validateDuplicate중복회원체크(){
        //given
        Member member1 = new Member();
        member1.setName("hello222");

        Member member2 = new Member();
        member2.setName("hello222");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //메세지검증 어떻게하지~~~ 반환이되는 assertThrows..!!! 반환값이 exception이라니....
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");

//        try {
//            memberService.join(member2);
//            fail(); // junit method
//        }catch (IllegalStateException e){ //e.getMessage() String
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
//        }

        //then

    }
    @Test
    void findMembers회원찾기() {
    }

    @Test
    void findOne회원찾기() {
    }
}