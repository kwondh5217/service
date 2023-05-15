package com.education.webservice.service;

import com.education.webservice.domain.academy.Academy;
import com.education.webservice.domain.academy.AcademyRepository;
import com.education.webservice.domain.comment.Comment;
import com.education.webservice.domain.comment.CommentRepository;
import com.education.webservice.domain.user.Role;
import com.education.webservice.domain.user.User;
import com.education.webservice.domain.user.UserRepository;
import com.education.webservice.dto.CommentSaveRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired private UserRepository userRepository;
    @Autowired private AcademyRepository academyRepository;
    @Autowired private CommentService commentService;
    @Autowired private CommentRepository commentRepository;

    @BeforeEach
    void clearAll(){
        userRepository.deleteAll();
        academyRepository.deleteAll();
        commentRepository.deleteAll();
    }

    @Test
    @DisplayName("회원이 등록된다")
    void save_user(){
        //given
        User user = User.builder()
                .name("kwon")
                .email("hello@gmail.com")
                .picture("no picture")
                .role(Role.USER)
                .build();
        //when
        User savedUser = userRepository.save(user);
        //then
        Assertions.assertThat(user.getName()).isEqualTo(savedUser.getName());
    }

    @Test
    @DisplayName("회원이 학원에 대한 댓글을 작성한다.")
    void save() {
        //given
        User user = User.builder()
                .name("kwon")
                .email("hello@gmail.com")
                .picture("no picture")
                .role(Role.USER)
                .build();
        Academy academy = Academy.builder()
                .city("seoul")
                .gu("강남구")
                .groupName("학원")
                .name("권대호영어학원")
                .fieldName("중등영어")
                .courseName("중등과정")
                .price("중1 : 10000원")
                .isOpen("Y")
                .addressNumber("12345")
                .address("서울시 강남구 123-12")
                .build();
        User savedUser = userRepository.save(user);
        Academy savedAcademy = academyRepository.save(academy);
        CommentSaveRequestDto saveRequestDto = new CommentSaveRequestDto();
        saveRequestDto.setUserId(savedUser.getId());
        saveRequestDto.setAcademyId(savedAcademy.getId());
        saveRequestDto.setContent("hello world!");
        //when
        Long savedComment = commentService.save(saveRequestDto);
        //then
        Optional<Comment> byId = commentRepository.findById(savedComment);
        Comment comment = byId.get();
        Assertions.assertThat(savedUser.getId()).isEqualTo(comment.getUser().getId());
    }

    @Test
    @DisplayName("학원 정보가 수정된다")
    void updateAcademy(){
        //given
        Academy academy = Academy.builder()
                .city("seoul")
                .gu("강남구")
                .groupName("학원")
                .name("권대호영어학원")
                .fieldName("중등영어")
                .courseName("중등과정")
                .price("중1 : 10000원")
                .isOpen("Y")
                .addressNumber("12345")
                .address("서울시 강남구 123-12")
                .build();

        academyRepository.save(academy);
        //when
        Academy updatedAcademy = academy.update("daegu", "강남구", "학원", "권대호영어학원",
                "중등영어", "중등과정", "중1 : 10000원",
                "Y", "12345", "서울시 강남구 123-12");
        //then
        Assertions.assertThat(updatedAcademy.getCity()).isEqualTo("daegu");
    }

    @Test
    @DisplayName("댓글이 수정된다")
    void updateComment(){
        //given
        User user = User.builder()
                .name("kwon")
                .email("hello@gmail.com")
                .picture("no picture")
                .role(Role.USER)
                .build();

        Academy academy = Academy.builder()
                .city("seoul")
                .gu("강남구")
                .groupName("학원")
                .name("권대호영어학원")
                .fieldName("중등영어")
                .courseName("중등과정")
                .price("중1 : 10000원")
                .isOpen("Y")
                .addressNumber("12345")
                .address("서울시 강남구 123-12")
                .build();

        User savedUser = userRepository.save(user);
        Academy savedAcademy = academyRepository.save(academy);

        CommentSaveRequestDto saveRequestDto = new CommentSaveRequestDto();
        saveRequestDto.setUserId(savedUser.getId());
        saveRequestDto.setAcademyId(savedAcademy.getId());
        saveRequestDto.setContent("hello world!");
        Long savedComment = commentService.save(saveRequestDto);
        //when
        Optional<Comment> findId = commentRepository.findById(savedComment);
        Comment comment = findId.get();
        String changedContent = "this";
        comment.update(changedContent);

        //then

        Optional<Comment> byId = commentRepository.findById(savedComment);
        Comment comment1 = byId.get();
        Assertions.assertThat(comment1.getContent()).isEqualTo(changedContent);

    }
}