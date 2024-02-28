package com.example.project.service;

import com.example.project.domain.Board;
import com.example.project.domain.Comment;
import com.example.project.dto.Commentdto;
import com.example.project.repository.BoardRepository;
import com.example.project.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Comment save(Commentdto commentdto) {
        commentdto.setCreateTime(LocalDateTime.now());
        Optional<Board> boardOptional = boardRepository.findById(commentdto.getBoardId());

        if(boardOptional.isPresent()){
            Board board = boardOptional.get();
            return commentRepository.save(commentdto.toEntity(board));
        }else{
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다: " + commentdto.getBoardId());
        }
    }


    public List<Commentdto> findAll(Long boardId) {
        // boardRepository를 사용해 boardId에 해당하는 게시글 찾기
        Board boardEntity = boardRepository.findById(boardId).get();

        // commentRepository를 사용 헤당 게시글에 속한 댓글 목록을 조회
        // 댓글은 ID를 기준으로 내림차순으로 정렬
        List<Comment> commentEntityList = commentRepository.findAllByBoardOrderByIdDesc(boardEntity);

        // 조회된 댓글 목록을 commentDTO로 변환하여 저장할 리스트 생성
        /* EntityList -> DTOList */
        List<Commentdto> commentDTOList = new ArrayList<>();

        //조회된 댓글 목록을 for문을 돌면서 commentDTO로 변환하고 리스트에 추가
        for (Comment commentEntity: commentEntityList) {
            Commentdto commentDTO = Commentdto.tocommentDTO(commentEntity, boardId);
            commentDTOList.add(commentDTO);
        }

        // commentDTO로 변환된 댓글 목록을 반환
        return commentDTOList;
    }

    @Transactional
    public void update(Long id, Commentdto commentDTO) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다: " + id));

        Optional<Comment> optionalComment = commentRepository.findByIdAndBoard(commentDTO.getId(), board);

        // 찾은 댓글이 있다면 수정 작업 진행
        if (optionalComment.isPresent()) {
            Comment foundComment = optionalComment.get();
            foundComment.updateCommentFromDTO(commentDTO);

            commentRepository.save(foundComment);

        } else {
            // 찾은 댓글이 없는 경우에 대한 처리
            throw new IllegalArgumentException("해당 댓글을 찾을 수 없습니다: " + commentDTO.getId());
        }

    }

    public Commentdto getCommentById(Long id) {
        // commentRepository를 사용하여 댓글 ID에 해당하는 댓글 찾기
        Optional<Comment> optionalComment = commentRepository.findById(id);

        // 찾은 댓글이 있다면 commentDTO로 변환하여 반환
        if (optionalComment.isPresent()) {
            Comment commentEntity = optionalComment.get();
            return Commentdto.tocommentDTO(commentEntity, commentEntity.getBoard().getId());
        } else {
            // 찾은 댓글이 없는 경우에 대한 처리 (null 반환 또는 예외 발생 등)
            // null 반환 예시:
            return null;

            // 예외 발생 예시:
            // throw new IllegalArgumentException("해당 댓글을 찾을 수 없습니다: " + id);
        }
    }

    public void delete(Long id, Long commentId) {
        // boardRepository를 사용해 boardId에 해당하는 게시글 찾기
        Board boardEntity = boardRepository.findById(id).get();

        // commentRepository를 사용하여 해당 게시글에 속한 댓글 중 commentId와 일치하는 댓글 찾기
        Optional<Comment> optionalComment = commentRepository.findByIdAndBoard(commentId, boardEntity);

        // 찾은 댓글이 있다면 삭제
        if (optionalComment.isPresent()) {
            Comment foundComment = optionalComment.get();
            commentRepository.delete(foundComment);
        } else {
            // 찾은 댓글이 없는 경우에 대한 처리 (예외 발생 또는 다른 방식의 처리)
            // 예외 발생 예시:
            throw new IllegalArgumentException("해당 댓글을 찾을 수 없습니다: " + commentId);
        }
    }
}
