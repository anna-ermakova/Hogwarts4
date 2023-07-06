package ru.hogwarts.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.repositorys.FacultyRepository;
import ru.hogwarts.school.services.FacultyService;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTests {

    @Mock
    private FacultyRepository repositoryMock;

    @InjectMocks
    FacultyService out;

    @Test
    void addFacultyTest() {
        Faculty f = new Faculty(4, "DDD", "green");
        Mockito.when(repositoryMock.save(f)).thenReturn(f);
        Assertions.assertEquals(f, out.addFaculty(f));
    }

    @Test
    void getFacultyPositiveTest() {
        Faculty f = new Faculty(4, "DDD", "green");
        Mockito.when(repositoryMock.findById(4L)).thenReturn(Optional.of(f));
        Assertions.assertEquals(f, out.getFaculty(4));
    }

    @Test
    void getFacultyNegativeTest() {
        Mockito.when(repositoryMock.findById(4L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> out.getFaculty(4));
    }

    @Test
    void editFacultyTest() {
        Faculty f = new Faculty(3, "CCC", "black");
        Mockito.when(repositoryMock.save(f)).thenReturn(f);
        Assertions.assertEquals(f, out.editFaculty(f));
    }


//    @Test
//    void removeFacultyPositiveTest() {
//        Faculty f = new Faculty(3, "CCC", "green");
//        int size = out.getAll().size();
//        assertEquals(f, out.removeFaculty(3));
//        assertEquals(size - 1, out.getAll().size());
//    }
//
//    @Test
//    void removeFacultyNegativeTest() {
//        int size = out.getAll().size();
//        assertNull(out.removeFaculty(4));
//        assertEquals(size, out.getAll().size());
//    }

    @Test
    void getFacultiesByColorPositiveTest() {
        Faculty f = new Faculty(4, "DDD", "green");
        Mockito.when(repositoryMock.findByColorLike("green")).thenReturn(List.of(f));
        Assertions.assertIterableEquals(List.of(f), out.getFacultiesByColor("green"));
    }

    @Test
    void getFacultiesByColorNegativeTest() {
        List<Faculty> test = Collections.emptyList();
        Mockito.when(repositoryMock.findByColorLike("black")).thenReturn(test);
        Assertions.assertIterableEquals(test, out.getFacultiesByColor("black"));
    }
}
