package appUser.stockdata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

    List<StockEntity> findAllByUserID(Long userid);

    @Transactional
    void deleteByUserIDEquals(Long userid);

}

