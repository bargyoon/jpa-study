package com.kh.bookmanager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.kh.bookmanager.common.jpaTemplate.JpaTemplate;
import com.kh.bookmanager.view.Index;

public class Run {

	public static void main(String[] args) {
		JpaTemplate.init();
		new Index().startMenu();

	}

}
