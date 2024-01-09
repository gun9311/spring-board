package com.example.board.domain;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "comment")
@ToString
public class Comment extends BaseEntity{
    @Id // pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column
    private String commentWriter;

    @Column(length = 500)
    private String commentContent;


    public static Comment toSaveEntity(CommentDTO commentDTO) {
        Comment commentEntity = new Comment();
        commentEntity.setCommentContent(commentDTO.getCommentContent());
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        return  commentEntity;
    }

    public static Comment toUpdateEntity(CommentDTO commentDTO) {
        Comment commentEntity = new Comment();
        commentEntity.setId(commentDTO.getId());
        commentEntity.setCommentContent(commentDTO.getCommentContent());
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCreatedTime(commentDTO.getCommentCreatedTime());
        return  commentEntity;
    }

    // Getter, Setter, Constructor 등 필요한 메서드 추가
}