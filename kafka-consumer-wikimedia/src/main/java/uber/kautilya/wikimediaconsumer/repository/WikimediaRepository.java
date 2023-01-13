package uber.kautilya.wikimediaconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uber.kautilya.wikimediaconsumer.entity.WikimediaEntity;

public interface WikimediaRepository extends JpaRepository<WikimediaEntity, Long> {
}
