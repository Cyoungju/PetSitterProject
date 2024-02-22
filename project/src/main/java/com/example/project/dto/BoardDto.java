package com.example.project.dto;


import com.example.project.domain.Board;
import com.example.project.domain.User;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String contents;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String userName;

    public BoardDto(Long id, String title, LocalDateTime createTime, String userName) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.userName = userName;
    }

    public BoardDto(Long id, String title, String contents, LocalDateTime createTime , String userName) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.createTime = createTime;
        this.userName = userName;
    }

    public static BoardDto toboardDTO(Board board) {
        return new BoardDto(
                board.getId(),
                board.getTitle(),
                board.getContents(),
                board.getCreateTime(),
                board.getUserName()
        );
    }


    public Board toEntity(){
        return Board.builder()
                .title(title)
                .contents(contents)
                .createTime(createTime)
                .updateTime(LocalDateTime.now())
                .userName(userName)
                .build();
    }


}
