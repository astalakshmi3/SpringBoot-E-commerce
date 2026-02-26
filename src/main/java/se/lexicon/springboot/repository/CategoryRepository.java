package se.lexicon.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.springboot.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository <Category,Long>{
    Optional <Category> findByNameIgnoreCase (String name);
    boolean existsByNameIgnoreCase (String name);

    List<Category> findByNameContaining (String keyword);
    long count();
}
