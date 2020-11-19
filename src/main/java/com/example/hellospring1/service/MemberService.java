package com.example.hellospring1.service;

import com.example.hellospring1.domain.Member;
import com.example.hellospring1.repository.MemberRepository;
import com.example.hellospring1.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member) {

        /* Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
        throw IllegalStateException("이미 존재");*/



        validateDuplicateMember(member); //중복 회원 검증 ctrl + T로 method로 서 뽑아냄
        memberRepository.save(member);
        return member.getId();
    }



    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 전체 회원 조회
     */


    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}


