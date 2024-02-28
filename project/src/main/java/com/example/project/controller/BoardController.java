package com.example.project.controller;

import com.example.project.domain.User;
import com.example.project.dto.BoardDto;
import com.example.project.dto.Commentdto;
import com.example.project.service.BoardService;
import com.example.project.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    private final CommentService commentService;

    // 페이지 이동
    //create
    @GetMapping("/write")
    public String write(){
        return "/board/write";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model){
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("board", boardDto);//html에 있는 이름 그대로 사용
        return "/board/update";
    }

    // =================================

    // crud
    // save
    @PostMapping()
    public String save(@ModelAttribute BoardDto boardDto){
        boardService.save(boardDto);
        return "redirect:/board/list";
    }

    // read - 전체 (페이징 작업)
    @GetMapping("/list")
    public String lists(@PageableDefault(page = 1) Pageable pageable, Model model){

        Page<BoardDto> boards = boardService.paging(pageable);

        int blockLimit = 3;
        int startPage = (int)(Math.ceil((double) pageable.getPageNumber() /blockLimit));
        int endPage = Math.min((startPage + blockLimit - 1), boards.getTotalPages());

        model.addAttribute("boardList", boards);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/board/list";
    }

    // read - 상세 페이지
    @GetMapping("/{id}")
    public String list(@PathVariable Long id, Model model, @PageableDefault(page = 1) Pageable pageable){
        BoardDto boardDto = boardService.findById(id);

        List<Commentdto> commentdtoList = commentService.findAll(id);

        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("board", boardDto);
        model.addAttribute("commentdtoList", commentdtoList);

        return "/board/detail";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDto boardDto){
        boardService.update(boardDto);
        return "redirect:/board/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        boardService.delete(id);
        return "redirect:/board/list";
    }


}
