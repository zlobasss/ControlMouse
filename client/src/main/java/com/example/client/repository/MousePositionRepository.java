package com.example.client.repository;

import com.example.client.entity.MousePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MousePositionRepository extends JpaRepository<MousePosition, Long> {
}
