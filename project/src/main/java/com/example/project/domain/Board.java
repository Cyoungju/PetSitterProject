package com.example.project.domain;


import com.example.project.dto.BoardDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 게시물 제목
    @Column(length = 100, nullable = false)
    private String title;

    // ** 내용
    @Column(length = 300)
    private String contents;

    // ** 작성 시간
    private LocalDateTime createTime;

    // ** 최근 수정 시간
    private LocalDateTime updateTime;

    private String userName;



    @Builder
    public Board(Long id, String title, String contents, LocalDateTime createTime, LocalDateTime updateTime,String userName) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userName = userName;
    }

    public void updateFromDTO(BoardDto boardDTO){
        this.userName = boardDTO.getUserName();
        this.title = boardDTO.getTitle();
        this.contents = boardDTO.getContents();
    }
}
