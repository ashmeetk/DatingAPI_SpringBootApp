package com.ashmeet.dating.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ashmeet.dating.entities.Interest;
import com.ashmeet.dating.entities.UserAccount;
import com.ashmeet.dating.repos.InterestRepository;
import com.ashmeet.dating.repos.UserAccountRepository;

import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api")
public class UserAccountController {
	
	@Autowired
	private UserAccountRepository userAccountRepo;
	
	@Autowired
	private InterestRepository interestRepo;
	
	@PostMapping("/users/register-user")
	public UserAccount registerUser(@RequestBody UserAccount userAccount){
		try {
			return this.userAccountRepo.save(userAccount);
		}
		catch(ConstraintViolationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	
	@PostMapping("/interest/update")
	public Interest updateInterest(@RequestBody Interest interest){
		UserAccount userAccount = userAccountRepo.findById(interest.getUserAccountId()).get();
		interest.setUserAccount(userAccount);
		return interestRepo.save(interest);
	}
	
	@RequestMapping("/users/get/all")
	public List<UserAccount> getAllUserAccountsAndInterests(){
		return this.userAccountRepo.findAll();
	}
	
	@DeleteMapping("/users/delete/{interestId}")
	public void deleteInterest(@PathVariable("interestId") int interestId){
		this.interestRepo.deleteById(interestId);
	}
	
	@GetMapping("/users/matches/{id}")
	public List<UserAccount> findMatches(@PathVariable("id") int id){
		UserAccount userAccount = userAccountRepo.findById(id).get();
		return userAccountRepo.findMatches(userAccount.getAge(), userAccount.getCity(), userAccount.getCountry(), userAccount.getId());
	}
}
