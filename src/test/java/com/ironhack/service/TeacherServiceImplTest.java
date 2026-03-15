package com.ironhack.service;

import com.ironhack.dto.request.CreateTeacherRequest;
import com.ironhack.dto.request.UpdateTeacherRequest;
import com.ironhack.dto.response.TeacherResponse;
import com.ironhack.mapper.TeacherMapper;
import com.ironhack.model.Teacher;
import com.ironhack.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeacherServiceImplTest {
    @Mock
    private TeacherMapper mapper;

    @InjectMocks
    private TeacherServiceImpl teacherService;


    private CreateTeacherRequest createTeacherRequest;
    private UpdateTeacherRequest updateTeacherRequest;

    @BeforeEach
    void setUp() {
        createTeacherRequest = new CreateTeacherRequest("John Doe", 50000.0);
    }

    @Test
    void createTeacher() {
        Teacher teacher = new Teacher(createTeacherRequest.name(), createTeacherRequest.salary());
        TeacherResponse expectedResponse = new TeacherResponse(teacher.getId(), teacher.getName(), teacher.getSalary());

        // Mock the mapper to return the expected response
        when(mapper.toModel(createTeacherRequest)).thenReturn(teacher);
        when(mapper.toResponse(teacher)).thenReturn(expectedResponse);

        // Call the service method
        TeacherResponse actualResponse = teacherService.create(createTeacherRequest);

        // Assert the response
        assertEquals(expectedResponse.id(), actualResponse.id());
        assertEquals(expectedResponse.name(), actualResponse.name());
        assertEquals(expectedResponse.salary(), actualResponse.salary());
    }

    @Test
    void updateTeacher() {
        Teacher teacher = new Teacher(createTeacherRequest.name(), createTeacherRequest.salary());
        TeacherResponse expectedResponse = new TeacherResponse(teacher.getId(), teacher.getName(), teacher.getSalary());

        // Mock the mapper to return the expected response
        when(mapper.toModel(createTeacherRequest)).thenReturn(teacher);
        when(mapper.toResponse(teacher)).thenReturn(expectedResponse);

        // Call the service method
        TeacherResponse actualResponse = teacherService.create(createTeacherRequest);

        String id = actualResponse.id();

        updateTeacherRequest = new UpdateTeacherRequest("Jane Doe", 60000.0);
        TeacherResponse updatedResponse = new TeacherResponse(id, updateTeacherRequest.name(), updateTeacherRequest.salary());
        when(mapper.toResponse(teacher)).thenReturn(updatedResponse);
        TeacherResponse actualUpdatedResponse = teacherService.update(id, updateTeacherRequest);

        // Assert the response
        assertEquals(updatedResponse.id(), actualUpdatedResponse.id());
        assertEquals(updatedResponse.name(), actualUpdatedResponse.name());
        assertEquals(updatedResponse.salary(), actualUpdatedResponse.salary());
    }

    @Test
    void deleteTeacher() {
        Teacher teacher = new Teacher(createTeacherRequest.name(), createTeacherRequest.salary());
        TeacherResponse expectedResponse = new TeacherResponse(teacher.getId(), teacher.getName(), teacher.getSalary());

        // Mock the mapper to return the expected response
        when(mapper.toModel(createTeacherRequest)).thenReturn(teacher);
        when(mapper.toResponse(teacher)).thenReturn(expectedResponse);

        // Call the service method
        TeacherResponse actualResponse = teacherService.create(createTeacherRequest);

        String id = actualResponse.id();

        teacherService.delete(id);

        assertThrows(RuntimeException.class, () -> teacherService.getById(id));
    }

    @Test
    void getTeacherById() {
        Teacher teacher = new Teacher(createTeacherRequest.name(), createTeacherRequest.salary());
        TeacherResponse expectedResponse = new TeacherResponse(teacher.getId(), teacher.getName(), teacher.getSalary());

        // Mock the mapper to return the expected response
        when(mapper.toModel(createTeacherRequest)).thenReturn(teacher);
        when(mapper.toResponse(teacher)).thenReturn(expectedResponse);

        // Call the service method
        TeacherResponse actualResponse = teacherService.create(createTeacherRequest);

        String id = actualResponse.id();

        TeacherResponse responseById = teacherService.getById(id);

        // Assert the response
        assertEquals(expectedResponse.id(), responseById.id());
        assertEquals(expectedResponse.name(), responseById.name());
        assertEquals(expectedResponse.salary(), responseById.salary());
    }

    @Test
    void getTeacherById_NotFound() {
        String nonExistentId = "non-existent-id";

        assertThrows(RuntimeException.class, () -> teacherService.getById(nonExistentId));
    }

    @Test
    void getTeacherByName() {
        Teacher teacher = new Teacher(createTeacherRequest.name(), createTeacherRequest.salary());
        TeacherResponse expectedResponse = new TeacherResponse(teacher.getId(), teacher.getName(), teacher.getSalary());

        // Mock the mapper to return the expected response
        when(mapper.toModel(createTeacherRequest)).thenReturn(teacher);
        when(mapper.toResponse(teacher)).thenReturn(expectedResponse);

        // Call the service method
        TeacherResponse actualResponse = teacherService.create(createTeacherRequest);

        String name = actualResponse.name();

        List<TeacherResponse> teachersByName = teacherService.getTeachers(name, null, null);

        // Assert the response
        assertEquals(1, teachersByName.size());
        assertEquals(expectedResponse.id(), teachersByName.get(0).id());
        assertEquals(expectedResponse.name(), teachersByName.get(0).name());
        assertEquals(expectedResponse.salary(), teachersByName.get(0).salary());
    }

    @Test
    void getTeacherByName_NotFound() {
        String nonExistentName = "Non Existent Name";

        List<TeacherResponse> teachersByName = teacherService.getTeachers(nonExistentName, null, null);

        // Assert the response
        assertEquals(0, teachersByName.size());
    }

    @Test
    void getTeacherBySalaryRange() {
        Teacher teacher = new Teacher(createTeacherRequest.name(), createTeacherRequest.salary());
        TeacherResponse expectedResponse = new TeacherResponse(teacher.getId(), teacher.getName(), teacher.getSalary());

        // Mock the mapper to return the expected response
        when(mapper.toModel(createTeacherRequest)).thenReturn(teacher);
        when(mapper.toResponse(teacher)).thenReturn(expectedResponse);

        // Call the service method
        TeacherResponse actualResponse = teacherService.create(createTeacherRequest);

        Double minSalary = 40000.0;
        Double maxSalary = 60000.0;

        List<TeacherResponse> teachersBySalaryRange = teacherService.getTeachers(null, minSalary, maxSalary);

        // Assert the response
        assertEquals(1, teachersBySalaryRange.size());
        assertEquals(expectedResponse.id(), teachersBySalaryRange.get(0).id());
        assertEquals(expectedResponse.name(), teachersBySalaryRange.get(0).name());
        assertEquals(expectedResponse.salary(), teachersBySalaryRange.get(0).salary());
    }

    @Test
    void getTeacherBySalaryRange_NotFound() {
        Double minSalary = 60000.0;
        Double maxSalary = 70000.0;

        List<TeacherResponse> teachersBySalaryRange = teacherService.getTeachers(null, minSalary, maxSalary);

        // Assert the response
        assertEquals(0, teachersBySalaryRange.size());
    }


}