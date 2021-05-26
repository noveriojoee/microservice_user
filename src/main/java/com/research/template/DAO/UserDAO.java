package com.research.template.DAO;


import com.research.template.DAO.Repository.IUserRepositories;
import com.research.template.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDAO {

	@Autowired
	IUserRepositories userRepository;

	private static List<UserModel> users = new ArrayList<>();

	static{
		users.add(new UserModel(1,"Donny"));
		users.add(new UserModel(2,"Reynard"));
		users.add(new UserModel(3,"Oscar"));
		users.add(new UserModel(4,"Ferial"));
	}

	public UserModel addUser(String name){
		int id = this.getSize()+1;
		UserModel createdUser = new UserModel(id,name);
		users.add(createdUser);

		this.userRepository.save(createdUser);

		return createdUser;
	}

	public UserModel deleteUser(int id){
		Optional<UserModel> userData = users.stream().filter(x -> x.getId() == id).findFirst();
		if (userData.isPresent()){
			users.remove(userData.get());
			this.userRepository.delete(userData.get());
			return userData.get();
		}else{
			return null;
		}
	}

	public List<UserModel> findAll(){
		return userRepository.findAll();
	}

	public UserModel findById(int id){
		Optional<UserModel> result = userRepository.findById(id);
		return result.get();
	}

	private int getSize(){
		return this.users.size();
	}
}
