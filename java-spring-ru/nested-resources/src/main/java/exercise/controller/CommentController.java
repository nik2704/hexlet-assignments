package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getComments(@PathVariable long postId) {
        return this.commentRepository.findAllByPostId(postId);
    }

    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getComment(@PathVariable long postId, @PathVariable long commentId) {
        return this.commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("PostID=" + postId + ", CommentID=" + commentId));
    }

    @PostMapping(path = "/{postId}/comments")
    public Comment postComment(@PathVariable long postId, @RequestBody Comment comment) {
        comment.setPost(postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("PostID=" + postId)));
        return commentRepository.save(comment);
//        return this.commentRepository.findAllByPostId(postId);
    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public void patchComment(@PathVariable long postId, @PathVariable long commentId, @RequestBody Comment comment) {

        Comment oldComment = this.commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("PostID=" + postId + ", CommentID=" + commentId));

        comment.setId(oldComment.getId());
        comment.setPost(postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("PostID=" + postId)));

        commentRepository.save(comment);
    }

    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable long postId, @PathVariable long commentId) {
        Comment comment = this.commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("PostID=" + postId + ", CommentID=" + commentId));

        commentRepository.delete(comment);
    }
    // END
}
