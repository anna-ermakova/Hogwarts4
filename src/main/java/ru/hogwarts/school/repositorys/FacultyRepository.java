package ru.hogwarts.school.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.entity.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findByColorLike(String color);

    Collection<Faculty> findAllByColorContainingIgnoreCaseOrNameContainingIgnoreCase(String color, String name);
}