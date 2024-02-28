package com.example.project.service;


import com.example.project.domain.Board;
import com.example.project.dto.BoardDto;
import com.example.project.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Transactional(readOnly = true)
@AllArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    private final UserService userService;

    @Transactional
    public void save(BoardDto boardDto) {
        boardDto.setCreateTime(LocalDateTime.now());
        boardRepository.save(boardDto.toEntity());

    }

    public Page<BoardDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int size = 5;

        Page<Board> boards = boardRepository.findAll(
                PageRequest.of(page,size)
        );

        return boards.map(board -> new BoardDto(
                board.getId(),
                board.getTitle(),
                board.getCreateTime(),
                board.getUserName()
        ));
    }

    public BoardDto findById(Long id) {
        Optional<Board> boardOptional  = boardRepository.findById(id);

        if(boardOptional.isPresent()){
            Board board = boardRepository.findById(id).get();
            return BoardDto.toboardDTO(board);
        }else {
            return null;
        }
    }

    @Transactional
    public void update(BoardDto boardDto) {
        Optional<Board> boardOptional = boardRepository.findById(boardDto.getId());


        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();
            board.updateFromDTO(boardDto);

            // 게시물 업데이트
            boardRepository.save(board);
        }
    }


    @Transactional
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
