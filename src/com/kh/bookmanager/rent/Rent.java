package com.kh.bookmanager.rent;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.kh.bookmanager.member.Member;

import lombok.Data;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class Rent {

	@Id
	@GeneratedValue
	private long rmIdx;
	
	@Column(columnDefinition = "date default sysdate")
	private Date regDate;
	@Column(columnDefinition = "number default 0")
	private boolean isReturn;
	private String title;
	@Column(nullable = true)
	private int rentBookCnt;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private Member member;
	
	//수정코드를 짜다가 문제가 생기면 단방향으로 수정하기
	//ToMany 관계일 경우
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="rmIdx")
	private List<RentBook> rentBooks = new ArrayList<RentBook>();
	
	
	
}
