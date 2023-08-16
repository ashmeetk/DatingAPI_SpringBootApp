package com.ashmeet.dating;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ashmeet.dating.controllers.UserAccountController;
import com.ashmeet.dating.entities.Interest;
import com.ashmeet.dating.entities.UserAccount;
import com.ashmeet.dating.repos.InterestRepository;
import com.ashmeet.dating.repos.UserAccountRepository;

@SpringBootTest
class DatingapiApplicationTests {
	
	@Mock
	UserAccountRepository userAccountRepository;
	
	@Mock
	InterestRepository interestRepository;
	
	@InjectMocks
	UserAccountController controller;

	@Test
	void testRegisterUser() {
		UserAccount userAccount = new UserAccount();
		UserAccount savedUserAccount = new UserAccount();
		savedUserAccount.setId(123);
		when(userAccountRepository.save(userAccount)).thenReturn(savedUserAccount);
		UserAccount outputUserAccount = controller.registerUser(userAccount);
		assertNotNull(outputUserAccount);
		assertNotNull(outputUserAccount.getId());
		assertEquals(123, outputUserAccount.getId());
		verify(userAccountRepository).save(userAccount);
	}
	
	@Test
	void testUpdateInterest() {
		Interest interest = new Interest();
		interest.setUserAccountId(123);
		when(userAccountRepository.findById(123)).thenReturn(Optional.of(new UserAccount()));
		Interest savedInterest = new Interest();
		savedInterest.setId(123);
		when(interestRepository.save(interest)).thenReturn(savedInterest);
		Interest outputInterest = controller.updateInterest(interest);
		assertNotNull(outputInterest);
		assertNotNull(outputInterest.getId());
		assertEquals(123, outputInterest.getId()); 
		verify(userAccountRepository).findById(123);
		verify(interestRepository).save(interest);
	}
	
	@Test
	void testGetUsers() {
		List<UserAccount> users = new ArrayList<>();
		users.add(new UserAccount());
		when(userAccountRepository.findAll()).thenReturn(users);
		List<UserAccount> outputList = controller.getAllUserAccountsAndInterests();
		assertNotNull(outputList);
		assertEquals(outputList.size(),1);
		verify(userAccountRepository).findAll();
	}
	
	@Test
	void testDeleteInterest(){
		doNothing().when(interestRepository).deleteById(123);
		controller.deleteInterest(123);
		verify(interestRepository).deleteById(123);
	}
	
	@Test 
	void testFindMatches(){
		UserAccount userAccount = new UserAccount();
		userAccount.setAge(25);
		userAccount.setCity("Delhi");
		userAccount.setCountry("India");
		userAccount.setId(1);
		when(userAccountRepository.findById(1)).thenReturn(Optional.of(userAccount));
		List<UserAccount> list = new ArrayList<>();
		list.add(new UserAccount());
		when(userAccountRepository.findMatches(25, "Delhi", "India", 1)).thenReturn(list);
		List<UserAccount> outputList = controller.findMatches(1);
		assertNotNull(outputList);
		assertEquals(outputList.size(),1);
		verify(userAccountRepository).findById(1);
		verify(userAccountRepository).findMatches(25, "Delhi", "India", 1);
		
	}

}
