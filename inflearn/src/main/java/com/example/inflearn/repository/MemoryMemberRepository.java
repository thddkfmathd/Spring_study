package com.example.inflearn.repository;

import com.example.inflearn.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private  static Map<Long,Member> store = new HashMap<>();
    private  static long sequence = 0;

    @Override
    public Member save(Member member) {
       member.setId(++sequence);
       store.put(member.getId(),member);
       return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null이어도 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // findAny() optional로 반환

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
