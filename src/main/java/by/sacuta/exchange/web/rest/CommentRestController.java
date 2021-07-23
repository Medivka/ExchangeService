package by.sacuta.exchange.web.rest;

import by.sacuta.exchange.dto.CommentDTO;
import by.sacuta.exchange.domain.model.Comment;
import by.sacuta.exchange.service.CommentService;
import by.sacuta.exchange.service.MyModelMapper;
import by.sacuta.exchange.service.impl.CommentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("rest/comment")
public class CommentRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentRestController.class);

    private final CommentService commentService;
    private final MyModelMapper myModelMapper;

    public CommentRestController(CommentService commentService, MyModelMapper myModelMapper) {
        this.commentService = commentService;
        this.myModelMapper = myModelMapper;
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<CommentDTO>> read() {
        LOGGER.info("rest/comment/get/all ");
        final List<CommentDTO> commentDTOList = new LinkedList<>();
        for (Comment c : commentService.getAll()
        ) {
            commentDTOList.add(myModelMapper.mapToCommentDTO(c));
        }
               return commentDTOList != null && !commentDTOList.isEmpty()
                ? new ResponseEntity<>(commentDTOList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<CommentDTO> read(@PathVariable(name = "id") long id) {
        LOGGER.info(String.format( "rest/comment/get/{%s} ",id));
        final CommentDTO commentDTO = myModelMapper.mapToCommentDTO(commentService.findById(id));
        return commentDTO != null
                ? new ResponseEntity<>(commentDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        LOGGER.info(String.format( "rest/comment/delete/{%s} ",id));

        boolean delete = false;
        final List<Comment> comments = commentService.getAll();
        for (Comment comment : comments) {
            if (comment.getId().equals(id)) {
                delete = true;
                commentService.delete(commentService.findById(id));
            }
        }
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> create(@RequestBody CommentDTO commentDTO) {
        LOGGER.info(String.format( "rest/save $s",commentDTO.getMessage()));
        commentService.save(myModelMapper.mapToComment(commentDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody CommentDTO commentDTO) {
        LOGGER.info(String.format( "rest/update/get/{%s} ",id));
        boolean update = false;
        final List<Comment> comments = commentService.getAll();
        for (Comment comment : comments) {
            if (comment.getId().equals(id)) {
                comment = myModelMapper.mapToComment(commentDTO);
                update = true;
                commentService.update(comment);
            }
        }
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
