package com.example.project_oop.repository;

import com.example.project_oop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
   UserEntity findUserByEmail(String email);
   UserEntity findUserByToken(String token);
   List<UserEntity> findAllByPassword(String password);
   List<UserEntity> findAllByEmail(String email);
/*   List<UserEntity>  findAllByPhone_number(String phoneNumber);*/
   List<UserEntity>  findAllByEmailAndPassword(String email,String password);


}
