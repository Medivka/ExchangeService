package by.sacuta.ExchangeService.service.api;

import by.sacuta.ExchangeService.dto.CommentDTO;
import by.sacuta.ExchangeService.dto.CourseDTO;
import by.sacuta.ExchangeService.dto.LessonDTO;
import by.sacuta.ExchangeService.dto.ProfileDTO;
import by.sacuta.ExchangeService.model.Comment;
import by.sacuta.ExchangeService.model.Course;
import by.sacuta.ExchangeService.model.Lesson;
import by.sacuta.ExchangeService.model.Profile;

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
