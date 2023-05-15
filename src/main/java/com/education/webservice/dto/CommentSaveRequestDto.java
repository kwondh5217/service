package com.education.webservice.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CommentSaveRequestDto {

    private Long userId;
    private Long academyId;
    private String content;

}
