package com.onnv.ChatAPI.repository;

import com.onnv.ChatAPI.entity.Test;
import com.onnv.ChatAPI.entity.Test1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Test1Repository extends JpaRepository<Test1, String> {
}
