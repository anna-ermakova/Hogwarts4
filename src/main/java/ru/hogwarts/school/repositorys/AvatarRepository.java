package ru.hogwarts.school.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.entity.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(Long studentId);
}