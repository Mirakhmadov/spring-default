package example.server.repository.mainReps;

import example.server.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    @Query(nativeQuery = true, value = "delete from attachment where id=:deleteId")
    void deleteAttachment(UUID deleteId);

}
