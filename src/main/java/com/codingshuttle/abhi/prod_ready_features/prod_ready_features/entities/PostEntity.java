package com.codingshuttle.abhi.prod_ready_features.prod_ready_features.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="posts")
@AllArgsConstructor
@NoArgsConstructor
@Audited
//@EntityListeners(AuditingEntityListener.class)
public class PostEntity extends AuditableEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

//    @PrePersist
//    void beforeSave(){
//
//    }
//
//    @PreUpdate
//    void beforeUpdate(){
//
//    }
//
//    @PreRemove
//    void beforeRemove(){
//
//    }
}
