package com.research.template.DAO;


import com.research.template.Model.UserModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO {
	private static List<UserModel> users;

	static{
		users.add(new UserModel(1,"Donny"));
		users.add(new UserModel(2,"Reynard"));
		users.add(new UserModel(3,"Oscar"));
		users.add(new UserModel(4,"Ferial"));
	}

	public void addUser(String name){
		int id = this.getSize()+1;
		users.add(new UserModel(id,name));
	}

	public void deleteUser(int id){
		UserModel result = null;
		for (UserModel data:users) {
			if (data.getId() == id){
				result = data;
				break;
			}
		}
		users.remove(result);
	}

	public List<UserModel> findAll(){
		return users;
	}

	public UserModel findById(int id){
		UserModel result = null;
		for (UserModel data:users) {
			if (data.getId() == id){
				result = data;
			}
		}
		return result;
	}

	private int getSize(){
		return this.users.size();
	}
}
