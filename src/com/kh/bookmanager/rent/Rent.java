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
	private Long rmIdx;
	
	@Column(columnDefinition = "date default sysdate")
	private Date regDate;
	@Column(columnDefinition = "number default 0")
	private Boolean isReturn;
	private String title;
	@Column(nullable = true)
	private Integer rentBookCnt;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private Member member;
	
	//수정코드를 짜다가 문제가 생기면 단방향으로 수정하기
	//ToMany 관계일 경우 필드를 초기화 해둘 것
	//Cascade Type
	// PERSIST : PERSIST를 수행할 때 연관엔티티도 함께 수행
	// MERGE : 준영속상태의 엔티티를 MERGE할 때 연관엔티티도 함께 MERGE
	// REMOVE : 엔티티를 삭제할 때 연관 엔티티도 함께 삭제
	// DETACH : 영속상태의 엔티티를 준영속 상태로 만들 떄 연관엔티티도 함께 수행
	// ALL : PERSIST + MERGE + REMOVE + DETACH 의 합
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="rmIdx")
	private List<RentBook> rentBooks = new ArrayList<RentBook>();
	
	
	
}
