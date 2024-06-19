package com.example.timesnap.domain.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.engine.spi.LoadQueryInfluencers;

@Entity
public final class ProjectDetail{

    /**
     * 그룹인증 세부정보 시퀀스
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 그룹인증 시퀀스
     */
    private Long groupid;

    /**
     * 회원시퀀스
     */
    private Long userid;

    /**
     * 인증 내용
     */
    private String content;

    /**
     * 첨부파일 명
     */
    private String filename;
}