package com.ysefa.productOrder.repository;

import com.ysefa.productOrder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
