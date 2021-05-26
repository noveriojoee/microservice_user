package com.research.template.DAO.Repository;


import com.research.template.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepositories extends JpaRepository<UserModel,Integer>{
}
