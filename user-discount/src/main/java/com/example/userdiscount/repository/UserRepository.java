package com.example.userdiscount.repository;

import com.example.userdiscount.domain.models.User;
import com.example.userdiscount.domain.models.UserID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UserID> {
}
