package by.sacuta.ExchangeService.service.api;

import by.sacuta.ExchangeService.model.dto.CommentDTO;
import by.sacuta.ExchangeService.model.dto.CourseDTO;
import by.sacuta.ExchangeService.model.dto.LessonDTO;
import by.sacuta.ExchangeService.model.dto.ProfileDTO;
import by.sacuta.ExchangeService.model.model.*;

public interface MyModelMapper {
    CommentDTO mapToCommentDTO(Comment comment);

    Comment mapToComment(CommentDTO commentDTO);

    CourseDTO mapToCourseDTO(Course course);

    Course mapToCourse(CourseDTO courseDTO);

    LessonDTO mapToLessonDTO(Lesson lesson);

    Lesson mapToLesson(LessonDTO lessonDTO);

    ProfileDTO mapToProfileDTO(Profile profile);

   Profile mapToProfile(ProfileDTO profileDTO);
}
