package poly.agile.webapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import poly.agile.webapp.dto.OrderDTO;
import poly.agile.webapp.model.Order;
import poly.agile.webapp.model.User;

@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query("SELECT new poly.agile.webapp.dto.OrderDTO(o.id, o.customerName, o.phoneNumber, o.address, o.createdTime, o.status.name) "
			+ "FROM Order o")
	Page<OrderDTO> getPages(Pageable pageable);

	List<Order> findByUser(User user);

	@Query("SELECT count(o) FROM Order o WHERE o.user = :user")
	long countNumberOfOrder(@Param("user") User user);

}
