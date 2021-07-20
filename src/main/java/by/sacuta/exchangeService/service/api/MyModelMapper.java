package by.sacuta.exchangeService.service.api;

import by.sacuta.exchangeService.model.dto.*;
import by.sacuta.exchangeService.model.model.*;

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
