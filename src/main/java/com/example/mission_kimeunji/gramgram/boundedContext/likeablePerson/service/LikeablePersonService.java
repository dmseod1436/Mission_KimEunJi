package com.example.mission_kimeunji.gramgram.boundedContext.likeablePerson.service;

import com.example.mission_kimeunji.gramgram.base.rq.Rq;
import com.example.mission_kimeunji.gramgram.base.rsData.RsData;
import com.example.mission_kimeunji.gramgram.boundedContext.instaMember.entity.InstaMember;
import com.example.mission_kimeunji.gramgram.boundedContext.instaMember.service.InstaMemberService;
import com.example.mission_kimeunji.gramgram.boundedContext.likeablePerson.entity.LikeablePerson;
import com.example.mission_kimeunji.gramgram.boundedContext.likeablePerson.repository.LikeablePersonRepository;
import com.example.mission_kimeunji.gramgram.boundedContext.member.entity.Member;
import com.example.mission_kimeunji.gramgram.boundedContext.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeablePersonService {
    private final LikeablePersonRepository likeablePersonRepository;
    private final InstaMemberService instaMemberService;
    private final MemberService memberService;
    private final Rq rq;

    @Transactional
    public RsData<LikeablePerson> like(Member member, String username, int attractiveTypeCode) {
        if (member.hasConnectedInstaMember() == false) {
            return RsData.of("F-2", "먼저 본인의 인스타그램 아이디를 입력해야 합니다.");
        }

        if (member.getInstaMember().getUsername().equals(username)) {
            return RsData.of("F-1", "본인을 호감상대로 등록할 수 없습니다.");
        }

        InstaMember toInstaMember = instaMemberService.findByUsernameOrCreate(username).getData();

        LikeablePerson likeablePerson = LikeablePerson
                .builder()
                .fromInstaMember(member.getInstaMember()) // 호감을 표시하는 사람의 인스타 멤버
                .fromInstaMemberUsername(member.getInstaMember().getUsername()) // 중요하지 않음
                .toInstaMember(toInstaMember) // 호감을 받는 사람의 인스타 멤버
                .toInstaMemberUsername(toInstaMember.getUsername()) // 중요하지 않음
                .attractiveTypeCode(attractiveTypeCode) // 1=외모, 2=능력, 3=성격
                .build();

        likeablePersonRepository.save(likeablePerson); // 저장

        return RsData.of("S-1", "입력하신 인스타유저(%s)를 호감상대로 등록되었습니다.".formatted(username), likeablePerson);
    }

    public List<LikeablePerson> findByFromInstaMemberId(Long fromInstaMemberId) {
        return likeablePersonRepository.findByFromInstaMemberId(fromInstaMemberId);
    }
    @Transactional
    public RsData<LikeablePerson> delete(Member member, Long id) {
        LikeablePerson likeablePerson = likeablePersonRepository.findById(id).get();
        if (!likeablePerson.getFromInstaMember().equals(member.getInstaMember())) {
            return RsData.of("F-1", "호감상대 삭제 권한이 없습니다.");
        }
        likeablePersonRepository.delete(likeablePerson);

        Optional<LikeablePerson> person = likeablePersonRepository.findById(id);
        if (person.isEmpty()){
            return RsData.of("F-2", "해당 호감상대가 존재하지 않습니다.");
        }

        return RsData.of("S-1", "해당 인스타유저(%s)가 호감상대 리스트에서 삭제되었습니다.".formatted(likeablePerson.getToInstaMember().getUsername()), likeablePerson);
    }
}