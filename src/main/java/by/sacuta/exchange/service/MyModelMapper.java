package by.sacuta.exchange.service;

import by.sacuta.exchange.dto.*;
import by.sacuta.exchange.domain.model.*;

public interface MyModelMapper {
    CommentDTO mapToCommentDTO(Comment comment);

    Comment mapToComment(CommentDTO commentDTO);

    CourseDTO mapToCourseDTO(Course course);

    Course mapToCourse(CourseDTO courseDTO);

    LessonDTO mapToLessonDTO(Lesson lesson);

    Lesson mapToLesson(LessonDTO lessonDTO);

    ProfileDTO mapToProfileDTO(Profile profile);

   Profile mapToProfile(ProfileDTO profileDTO);

    SectionDTO mapToSectionDTO(Section section);

    Section mapToSection(SectionDTO sectionDTO);
}
