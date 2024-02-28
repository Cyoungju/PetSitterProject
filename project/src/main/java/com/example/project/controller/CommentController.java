package com.example.project.controller;


import com.example.project.domain.Comment;
import com.example.project.dto.Commentdto;
import com.example.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/comment")
@Controller
public class CommentController {
    private final CommentService commentService;


    @PostMapping("/save")
    public ResponseEntity<?> save(@ModelAttribute Commentdto commentdto){

        Comment comment = commentService.save(commentdto);

        //저장된 댓글가져오기
        List<Commentdto> all = commentService.findAll(commentdto.getBoardId());

        //예외처리 해주기
        if(comment != null){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }

    }

    /* UPDATE */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @ModelAttribute Commentdto commentDTO) {
        commentService.update(id, commentDTO);
        return ResponseEntity.ok(id);
    }


    /* GET COMMENT BY ID */
    @GetMapping("/getComment/{id}")
    public ResponseEntity<Commentdto> getComment(@PathVariable Long id) {

        Commentdto comment = commentService.getCommentById(id);

        if (comment != null) {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}/comments/{commentId}")
    public ResponseEntity<Long> delete(@PathVariable Long id, @PathVariable Long commentId) {
        commentService.delete(id, commentId);
        return ResponseEntity.ok(commentId);
    }
}
