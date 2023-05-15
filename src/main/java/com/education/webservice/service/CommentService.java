package com.education.webservice.service;


import com.education.webservice.domain.academy.Academy;
import com.education.webservice.domain.academy.AcademyRepository;
import com.education.webservice.domain.comment.Comment;
import com.education.webservice.domain.comment.CommentRepository;
import com.education.webservice.domain.user.User;
import com.education.webservice.domain.user.UserRepository;
import com.education.webservice.dto.CommentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AcademyRepository academyRepository;

    @Transactional
    public Long save(CommentSaveRequestDto commentSaveRequestDto){
        User user = userRepository.findById(commentSaveRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Academy academy = academyRepository.findById(commentSaveRequestDto.getAcademyId())
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 정보입니다."));

        Comment comment = Comment.createComment(user, academy, commentSaveRequestDto.getContent());
        commentRepository.save(comment);

        return comment.getId();
    }
}
