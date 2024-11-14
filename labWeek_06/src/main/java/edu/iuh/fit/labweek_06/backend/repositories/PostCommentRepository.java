package edu.iuh.fit.labweek_06.backend.repositories;

import edu.iuh.fit.labweek_06.backend.models.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}