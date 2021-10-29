package com.kh.bookmanager.rent;

import java.util.List;

public class RentController {
	
	private RentService rentService = new RentService();
	
	public boolean registRent(String userId, List<Long> bkIdxs) {
		return rentService.insertRentInfo(userId, bkIdxs);
	}

	public void returnBook(String rbIdx) {
		rentService.returnBook(rbIdx);
	}

	public List<Rent> searchRentList(String userId) {
		
		return rentService.findRentByUserId(userId);
	}

	public Boolean returnBook(Long rmIdx) {
		return rentService.returnBook(rmIdx);
		
	}

	public Boolean extensionRentBook(Long rbIdx) {
		return rentService.extensionRentBook(rbIdx);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
