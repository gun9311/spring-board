package com.example.board.dto;
import com.example.board.domain.Board;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
// DTO(Data Transfer Object), VO, Bean
public class BoardDTO {
    private Long id;
    private  String boardWriter;
    private  String boardContent;
    private  String boardTitle;
    private int boardHits; // 조회수
    private LocalDateTime boardCreatedTime; // 작성시간
    private LocalDateTime boardUpdatedTime; // 수정시간

    public static  BoardDTO toBoardDTO(Board boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardContent(boardEntity.getBoardContent());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
        boardDTO.setId(boardEntity.getId());
        return  boardDTO;
    }
}
