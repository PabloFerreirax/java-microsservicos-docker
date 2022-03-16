package com.java.back.end.casa.codigo.repository;

import com.java.back.end.casa.codigo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
