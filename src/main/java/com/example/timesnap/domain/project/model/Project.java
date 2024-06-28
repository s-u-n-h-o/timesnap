package com.example.timesnap.domain.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
public final class Project {

    /**
     * 그룹 시퀀스
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 회원시퀀스
     */
    private Long userid;

    /**
     * 그룹 명
     */
    private String projectTitle;

    /**
     * 그룹 소개글
     */
    private String projectcontent;

    public Project() {

    }
}
