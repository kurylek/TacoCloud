package sia.tacos.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import sia.tacos.model.Taco;

public interface TacoRepository
        extends PagingAndSortingRepository<Taco, Long> {
}
