package project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.model.TV;

import java.util.List;

@Repository
public interface TVRepository extends CrudRepository<TV, Integer> {
    List<TV> findTVSByDiagonalGreaterThan(int power);
}
