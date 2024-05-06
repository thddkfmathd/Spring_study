package com.example.inflearn.service;

import com.example.inflearn.domain.Member;
import com.example.inflearn.repository.MemberRepository;
import com.example.inflearn.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    // 왜 final
    private final MemberRepository memberRepository ;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     * @return Long (id반환)
     */
    public Long join(Member member){
        //중복 이름 체크 ctrol alt v 타입변수 알아서
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원");
//        });
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    //extract method 단축키 ctrl alt m
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원");
        });
    }

    /**
     * 전체 회원조회
     * @return
     */
    public List<Member> findMembers(){ //repository단과 다르게 비즈니스에 가까운 용어 사용
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
