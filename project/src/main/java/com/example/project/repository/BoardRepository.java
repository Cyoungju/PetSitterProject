package com.example.project.repository;

import com.example.project.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {
    Optional<Board> findById(Long id);
}
