package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.dto.BoardDTO;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// DTO -> Entity
// Entity -> DTO

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        Board boardEntity = Board.toSaveEntity(boardDTO);
//        System.out.println("BoardService"+boardEntity);
        boardRepository.save(boardEntity);


    }

    public List<BoardDTO> findAll() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (Board board: boardList) {
            boardDTOList.add(BoardDTO.toBoardDTO(board));
        }
        return  boardDTOList;
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);


    }

    public BoardDTO findById(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            BoardDTO boardDTO = BoardDTO.toBoardDTO(optionalBoard.get());
            return boardDTO;
        }
        return null;
    }
}
