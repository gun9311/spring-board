package com.example.board.domain;
// DB의 테이블 역할을 하는 클래스
import com.example.board.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "board_table")
@ToString
public class Board extends BaseEntity{
    @Id // pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(length = 20, nullable = false) // 크기 20, not null
    private String boardTitle;

    @Column
    private String boardWriter;

    @Column(length = 500)
    private String boardContent;

    @Column
    private  int boardHits;


    public static Board toSaveEntity(BoardDTO boardDTO) {
        Board boardEntity = new Board();
        boardEntity.setBoardContent(boardDTO.getBoardContent());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardHits(0);
        return  boardEntity;
    }

    // Getter, Setter, Constructor 등 필요한 메서드 추가
}