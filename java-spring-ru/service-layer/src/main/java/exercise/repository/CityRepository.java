package exercise.repository;

import exercise.model.City;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
    // BEGIN
    List<City> findByNameStartsWithIgnoreCase(String name);

    List<City> findByOrderByNameAsc();

//    @Override
//    default void customize(QuerydslBindings bindings, QCity city) {
//        bindings.bind(city.name).first(
//                (StringPath path, String value) -> path.startsWithIgnoreCase(value)
//        );
//    }

    // END
}
