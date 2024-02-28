package com.example.project.domain;


import com.example.project.dto.Commentdto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String writer;

    @Column(length = 300)
    private String contents;


    private LocalDateTime createTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;


    @Builder
    public Comment(Long id, String writer, String contents, LocalDateTime createTime, Board board) {
        this.id = id;
        this.writer = writer;
        this.contents = contents;
        this.createTime = createTime;
        this.board = board;
    }

    //DTO -> Entity
    public void updateCommentFromDTO(Commentdto commentDTO) {
        this.writer = commentDTO.getWriter();
        this.contents = commentDTO.getContents();
    }
}
