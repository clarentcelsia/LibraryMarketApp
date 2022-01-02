package com.project.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Post {
    @Id
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    @GeneratedValue(generator = "uuid-generator")
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @ManyToOne(targetEntity = Topic.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", foreignKey = @ForeignKey(name = "fk_Post_topic_id"))
    @JsonBackReference
    private Topic topic;

//    @ManyToOne(targetEntity = Topic.class, fetch = FetchType.EAGER)
//    @JoinColumn(name = "topic_id", foreignKey = @ForeignKey(name = "fk_Post_topic_id"))
//    private Topic topic;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id",nullable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_post_user_id"))
    private User user;

    @CreatedDate
    @Column(updatable = false)
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @PrePersist
    public void createDate(){
        if ( createdAt == null) createdAt = new Date();
        if ( updatedAt == null) updatedAt = new Date();
    }

    @PreUpdate
    public void updateDate(){
        updatedAt = new Date();
    }

    public Post(String title, String body, Topic topic, User user, Date createdAt, Date updatedAt) {
        this.title = title;
        this.body = body;
        this.topic = topic;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
