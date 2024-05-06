package videominer.controller;

import videominer.exceptions.CommentNotFoundException;
import videominer.model.Comment;
import videominer.repository.ChannelRepository;
import videominer.repository.CommentRepository;
import videominer.repository.UserRepository;
import videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

// Uri: http://localhost:8080/api/videominer/channels/{channelId}/videos/{videoId}/comments
@RestController
@RequestMapping("/api/videominer/channels")
public class CommentController {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    CommentRepository commentRepository;

    //Obtener un comentario con su id
    //GET http://localhost:8080/videominer/comments/:id
    @GetMapping("/{id}")
    public Comment findOne(@PathVariable Long id) throws CommentNotFoundException {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isEmpty()) {throw new CommentNotFoundException();}
        return comment.get();
    }

    //Obtener todos los comentarios
    //GET http://localhost:8080/videominer/comments

    @GetMapping
    public List<Comment> findAll(){return commentRepository.findAll();}
}
