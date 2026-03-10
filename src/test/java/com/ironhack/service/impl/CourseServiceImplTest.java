package com.ironhack.service.impl;

import com.ironhack.dto.request.CreateCourseRequest;
import com.ironhack.dto.response.CourseResponse;
import com.ironhack.dto.response.TeacherResponse;
import com.ironhack.mapper.CourseMapper;
import com.ironhack.model.Course;
import com.ironhack.model.Teacher;
import com.ironhack.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {
    @Mock
    private TeacherService teacherService;

    @Mock
    private CourseMapper mapper;

    @InjectMocks
    private CourseServiceImpl courseService;

    private Teacher teacher;
    private TeacherResponse teacherResponse;

    @BeforeEach
    void setUp() {
        teacher = new Teacher(
                "Joao",
                600.0
        );
        teacherResponse = new TeacherResponse(
                "teacher-id",
                teacher.getName(),
                teacher.getSalary()
        );
    }

    @Test
    public void create_withTeacher_teacherExists_returnsResponse() {
        CreateCourseRequest request = new CreateCourseRequest(
                "Java Development",
                100.0,
                250.0,
                "teacher-id"
        );

        Course course = new Course(
                "Java Development",
                100.0
        );
        course.setMoneyEarned(250.0);

        CourseResponse expectedResponse = new CourseResponse(
                course.getId(),
                course.getName(),
                course.getPrice(),
                course.getMoneyEarned(),
                teacherResponse
        );

        when(mapper.toModel(request)).thenReturn(course);
        when(teacherService.getModelById(request.teacherId())).thenReturn(teacher);
        when(mapper.toResponse(course)).thenReturn(expectedResponse);

        CourseResponse response = courseService.create(request);

        verify(teacherService, times(1)).getModelById(request.teacherId());
        assertNotNull(response);
        assertNotNull(response.id());
        assertEquals(expectedResponse.name(), response.name());
        assertEquals(expectedResponse.price(), response.price());
        assertEquals(expectedResponse.moneyEarned(), response.moneyEarned());
        assertEquals(expectedResponse.teacher(), response.teacher());
    }

    @Test
    public void create_withoutTeacher_returnsResponse() {
        CreateCourseRequest request = new CreateCourseRequest(
                "Java Development",
                100.0,
                250.0,
                null
        );

        Course course = new Course(
                "Java Development",
                100.0
        );
        course.setMoneyEarned(250.0);

        CourseResponse expectedResponse = new CourseResponse(
                course.getId(),
                course.getName(),
                course.getPrice(),
                course.getMoneyEarned(),
                null
        );

        when(mapper.toModel(request)).thenReturn(course);
        when(mapper.toResponse(course)).thenReturn(expectedResponse);

        CourseResponse response = courseService.create(request);

        verify(teacherService, never()).getModelById(anyString());
        assertNotNull(response);
        assertNotNull(response.id());
        assertEquals(expectedResponse.name(), response.name());
        assertEquals(expectedResponse.price(), response.price());
        assertEquals(expectedResponse.moneyEarned(), response.moneyEarned());
        assertNull(response.teacher());
    }
}