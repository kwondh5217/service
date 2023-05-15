package com.education.webservice.domain.comment;


import com.education.webservice.domain.academy.Academy;
import com.education.webservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor
@Getter @Setter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String userName;

    @ManyToOne
    @JoinColumn(name = "academy_id")
    private Academy academy;

    private String content;

    @Builder
    public Comment(User user, Academy academy, String content) {
        this.user = user;
        this.academy = academy;
        this.content = content;
    }

    //==생성 메서드==//
    public static Comment createComment(User user, Academy academy, String content){
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setAcademy(academy);
        comment.setContent(content);

        return comment;
    }
    /**
     * 수정
     */
    public Comment update(String content){
        this.content = content;
        return this;
    }
}
