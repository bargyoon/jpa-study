package com.kh.bookmanager.rent;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.kh.bookmanager.book.Book;

import lombok.Data;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class RentBook {
	
	@Id
	@GeneratedValue
	private long rbIdx;
	
	@ManyToOne
	@JoinColumn(name="bkIdx")
	private Book book;
	

	@Column(columnDefinition = "date default sysdate")
	private Date regDate;
	private String state;
	@Column(columnDefinition = "date default sysdate+7")
	private Date returnDate;
	@Column(columnDefinition = "number default 0")
	private int extensionCnt;
	

}
