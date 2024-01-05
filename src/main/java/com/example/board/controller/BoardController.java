package com.example.board.controller;

import com.example.board.dto.BoardDTO;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private  final BoardService boardService;

    @GetMapping ("/create")
    public String createForm() {

        return  "boards/create";
    }

    @PostMapping ("/create")
    public String saveForm(@ModelAttribute BoardDTO boardDTO) {
//      System.out.println("board =" + boardDTO);
        boardService.save(boardDTO);
        return  "index";
    }

    @GetMapping ("/list")
    public String findAll(Model model) {
        //DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return  "boards/list";
    }

    @GetMapping ("/read{id}")
    public String findById(@PathVariable Long id, Model model)
    {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return  "boards/read";
    }

}
