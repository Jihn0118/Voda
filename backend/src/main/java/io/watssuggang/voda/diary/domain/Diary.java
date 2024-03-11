package io.watssuggang.voda.diary.domain;

import io.watssuggang.voda.common.converter.EmotionConverter;
import io.watssuggang.voda.common.domain.BaseEntity;
import io.watssuggang.voda.common.enums.Emotion;
import io.watssuggang.voda.member.domain.Member;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer diaryId;

    @Column(columnDefinition = "text")
    private String diaryContent;

    @Column(columnDefinition = "text")
    private String diarySummary;

    @Convert(converter = EmotionConverter.class)
    @Column(columnDefinition = "char(2)", length = 2)
    private Emotion diaryEmotion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "diary")
    List<DiaryFile> diaryFiles = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "diary")
    List<Talk> diaryTalks = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member;


    public void addMember(Member member) {
        member.getDiaries().add(this);
        this.member = member;
    }

    public void addDiaryFiles(DiaryFile diaryFile) {
        this.diaryFiles.add(diaryFile);
    }

    public void addDiaryTalks(Talk talk) {
        this.diaryTalks.add(talk);
    }

    @Builder
    public Diary(String diaryContent, String diarySummary, Emotion diaryEmotion, Member member) {
        this.diaryContent = diaryContent;
        this.diarySummary = diarySummary;
        this.diaryEmotion = diaryEmotion;
        this.member = member;
    }
}
