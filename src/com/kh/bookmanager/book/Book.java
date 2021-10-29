package com.kh.bookmanager.book;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class Book {
	
	@Id
	@GeneratedValue //JPA정책에 따라서 식별자를 자동 생성
	private Long bkIdx;
	private String isbn;
	private String category;
	private String title;
	private String author;
	private String info;
	@Column(columnDefinition = "number default 1")
	private Integer bookAmt;
	@Column(columnDefinition = "date default sysdate")
	private Date regDate;
	@Column(columnDefinition = "number default 0")
	private Integer rentCnt;
	
	
	
}
