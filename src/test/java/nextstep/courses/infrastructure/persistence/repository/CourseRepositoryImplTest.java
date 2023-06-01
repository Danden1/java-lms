package nextstep.courses.infrastructure.persistence.repository;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
public class CourseRepositoryImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryImplTest.class);

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void crud() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L,"1기");
        Long rowId = courseRepository.save(course);
        assertThat(rowId).isEqualTo(1);
        Course savedCourse = courseRepository.findById(1L);
        assertThat(course.getTitle()).isEqualTo(savedCourse.getTitle());
        LOGGER.debug("Course: {}", savedCourse);
    }
}
