package by.sacuta.ExchangeService.service;

import by.sacuta.ExchangeService.model.dto.CommentDTO;
import by.sacuta.ExchangeService.model.dto.CourseDTO;
import by.sacuta.ExchangeService.model.dto.LessonDTO;
import by.sacuta.ExchangeService.model.dto.ProfileDTO;
import by.sacuta.ExchangeService.model.model.*;
import by.sacuta.ExchangeService.service.api.MyModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MyModelMapperImpl implements MyModelMapper {
    private final ModelMapper modelMapper;

    public MyModelMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDTO mapToCommentDTO(Comment comment) {

        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        return commentDTO;
    }

    @Override
    public Comment mapToComment(CommentDTO commentDTO) {
//        logger.log(Level.INFO, "map to commentDTO " + commentDTO);
        return modelMapper.map(commentDTO, Comment.class);
    }

    @Override
    public CourseDTO mapToCourseDTO(Course course) {
   //     logger.log(Level.INFO, "map To CourseDTO " + course);
        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public Course mapToCourse(CourseDTO courseDTO) {
//        logger.log(Level.INFO, "map To Course " + courseDTO.getId());
        return modelMapper.map(courseDTO, Course.class);
    }

    @Override
    public LessonDTO mapToLessonDTO(Lesson lesson) {
//        logger.log(Level.INFO, "map To LessonDTO " + lesson);
        return modelMapper.map(lesson, LessonDTO.class);
    }

    @Override
    public Lesson mapToLesson(LessonDTO lessonDTO) {
//        logger.log(Level.INFO, "map To Lesson " + lessonDTO);
        return modelMapper.map(lessonDTO, Lesson.class);
    }

    @Override
    public ProfileDTO mapToProfileDTO(Profile profile) {
//        logger.log(Level.INFO, "map To ClientDTO " + clients);
        return modelMapper.map(profile, ProfileDTO.class);
    }

    @Override
    public Profile mapToProfile(ProfileDTO profileDTO) {
//        logger.log(Level.INFO, "map To clients " + clientDTO);
        return modelMapper.map(profileDTO, Profile.class);
    }




}
