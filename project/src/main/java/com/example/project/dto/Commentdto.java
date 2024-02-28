package com.example.project.dto;


import com.example.project.domain.Board;
import com.example.project.domain.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Commentdto {
    private Long id;

    private String writer;

    private String contents;

    private LocalDateTime createTime;

    private Long boardId;

    public Comment toEntity(Board board) {
        return Comment.builder()
                .writer(writer)
                .contents(contents)
                .createTime(createTime)
                .board(board)
                .build();
    }

    public static Commentdto tocommentDTO(Comment comment,Long boardId){
        return new Commentdto(
                comment.getId(),
                comment.getWriter(),
                comment.getContents(),
                comment.getCreateTime(),
                boardId
        );
    }
}
