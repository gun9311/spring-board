package com.example.board.dto;

import com.example.board.domain.Board;
import com.example.board.domain.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
// DTO(Data Transfer Object), VO, Bean
public class CommentDTO {
    private Long id;
    private  String commentWriter;
    private  String commentContent;
    private LocalDateTime commentCreatedTime; // 작성시간
    private LocalDateTime commentUpdatedTime; // 수정시간

    public static  CommentDTO toCommentDTO(Comment commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentContent(commentEntity.getCommentContent());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
        commentDTO.setCommentUpdatedTime(commentEntity.getUpdatedTime());
        commentDTO.setId(commentEntity.getId());
        return  commentDTO;
    }
}
