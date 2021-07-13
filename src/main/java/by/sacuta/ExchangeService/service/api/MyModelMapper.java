package by.sacuta.ExchangeService.service.api;

import by.sacuta.ExchangeService.model.dto.CommentDTO;
import by.sacuta.ExchangeService.model.dto.CourseDTO;
import by.sacuta.ExchangeService.model.dto.LessonDTO;
import by.sacuta.ExchangeService.model.dto.ProfileDTO;
import by.sacuta.ExchangeService.model.model.Role;
import by.sacuta.ExchangeService.model.model.Comment;
import by.sacuta.ExchangeService.model.model.Course;
import by.sacuta.ExchangeService.model.model.Lesson;

public interface MyModelMapper {
    CommentDTO mapToCommentDTO(Comment comment);

    Comment mapToComment(CommentDTO commentDTO);

    CourseDTO mapToCourseDTO(Course course);

    Course mapToCourse(CourseDTO courseDTO);

    LessonDTO mapToLessonDTO(Lesson lesson);

    Lesson mapToLesson(LessonDTO lessonDTO);

    ProfileDTO mapToProfileDTO(Role.Profile profile);

    Role.Profile mapToProfile(ProfileDTO profileDTO);
}
