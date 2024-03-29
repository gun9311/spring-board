package com.example.board.controller;

import com.example.board.domain.Member;
import com.example.board.dto.BoardDTO;
import com.example.board.dto.MemberResponseDto;
import com.example.board.service.BoardService;
import com.example.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private  final BoardService boardService;
    private  final MemberService memberService;

    @GetMapping ("/create")
    public String createForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String loggedInUserEmail = authentication.getName();
            MemberResponseDto loggedInUser = memberService.findMemberInfoById(Long.valueOf(loggedInUserEmail));
            model.addAttribute("loggedInUserEmail", loggedInUser.getEmail());
        }

        return  "boards/create";
    }

    @PostMapping ("/create")
    public String saveForm(@ModelAttribute BoardDTO boardDTO) {
//      System.out.println("board =" + boardDTO);
        boardService.save(boardDTO);
        return  "redirect:/boards/paging";
    }

    @GetMapping ("/list")
    public String findAll(Model model) {
        //DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return  "boards/list";
    }

    // test
    @GetMapping ("/read{id}")
    public String findById(@PathVariable("id") Long id, Model model, @PageableDefault(page = 1) Pageable pageable)
    {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return  "boards/read";
    }

    @GetMapping("/update{id}")
    public  String updateForm(@PathVariable("id") Long id, Model model, @PageableDefault(page = 1) Pageable pageable) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "boards/update";

    }

    @PostMapping("/update{id}")
    public  String update(@ModelAttribute BoardDTO boardDTO, Model model, @PageableDefault(page = 1) Pageable pageable) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        model.addAttribute("page", pageable.getPageNumber());
        return "redirect:/boards/read{id}?page=" + pageable.getPageNumber();
    }

    @GetMapping("/delete{id}")
    public  String delete(@PathVariable("id") Long id, Model model) {
        boardService.delete(id);
        return "redirect:/boards/paging";
    }

    @GetMapping("/paging")
    public  String paging(@PageableDefault(page=1) Pageable pageable, Model model) {
//        pageable.getPageNumber();
        Page<BoardDTO> boardList = boardService.paging(pageable);
        int blockLimit = 5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber()/ blockLimit)))-1) * blockLimit +1;
        int endPage = ((startPage + blockLimit -1) < boardList.getTotalPages()) ? startPage + blockLimit -1 : boardList.getTotalPages();
        // page 개수가 20개
        // 현재 사용자가 3페이지
        // 3 4 5 처럼
        // 보여지는 페이지 3개
        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return  "boards/paging";

    }
}
