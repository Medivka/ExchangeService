package by.sacuta.ExchangeService.service;

import by.sacuta.ExchangeService.model.dto.*;
import by.sacuta.ExchangeService.model.model.*;
import by.sacuta.ExchangeService.service.api.MyModelMapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyModelMapperImpl implements MyModelMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyModelMapperImpl.class);
    private final ModelMapper modelMapper;

    public MyModelMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDTO mapToCommentDTO(Comment comment) {
        LOGGER.info("mapToCommentDTO " + comment.getId());
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        return commentDTO;
    }

    @Override
    public Comment mapToComment(CommentDTO commentDTO) {
        LOGGER.info("mapToComment " + commentDTO.getId());
        return modelMapper.map(commentDTO, Comment.class);
    }

    @Override
    public CourseDTO mapToCourseDTO(Course course) {
        LOGGER.info("mapToCourseDTO " + course.getId());
        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public Course mapToCourse(CourseDTO courseDTO) {
        LOGGER.info("mapToCourse " + courseDTO.getId());
        return modelMapper.map(courseDTO, Course.class);
    }

    @Override
    public LessonDTO mapToLessonDTO(Lesson lesson) {
        LOGGER.info("mapToLessonDTO " + lesson.getId());
        return modelMapper.map(lesson, LessonDTO.class);
    }

    @Override
    public Lesson mapToLesson(LessonDTO lessonDTO) {
        LOGGER.info("mapToLesson " + lessonDTO.getId());
        return modelMapper.map(lessonDTO, Lesson.class);
    }

    @Override
    public ProfileDTO mapToProfileDTO(Profile profile) {
        LOGGER.info("mapToProfileDTO " + profile.getId());
        return modelMapper.map(profile, ProfileDTO.class);
    }

    @Override
    public Profile mapToProfile(ProfileDTO profileDTO) {
        LOGGER.info("mapToProfile " + profileDTO.getId());
        return modelMapper.map(profileDTO, Profile.class);
    }

    @Override
    public SectionDTO mapToSectionDTO(Section section) {
        LOGGER.info("mapToSectionDTO " + section.getId());
        return modelMapper.map(section, SectionDTO.class);
    }

    @Override
    public Section mapToSection(SectionDTO sectionDTO) {
        LOGGER.info("mapToSection " + sectionDTO.getId());
        return modelMapper.map(sectionDTO, Section.class);
    }
}
