package poly.agile.webapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import poly.agile.webapp.dto.OrderDTO;
import poly.agile.webapp.dto.OrderLineDTO;
import poly.agile.webapp.model.Order;
import poly.agile.webapp.model.User;

@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query("SELECT new poly.agile.webapp.dto.OrderDTO"
			+ "(o.id, o.customerName, o.phoneNumber, o.address, o.createdTime, o.status.name) " + "FROM Order o")
	Page<OrderDTO> getPages(Pageable pageable);

	@Query("SELECT new poly.agile.webapp.dto.OrderDTO"
			+ "(o.id, o.customerName, o.phoneNumber, o.address, o.createdTime, o.amount, o.status.name) "
			+ "FROM Order o WHERE o.user.username = :username")
	List<OrderDTO> findOrderListByUsername(@Param("username") String username);

	@Query("SELECT count(o) FROM Order o WHERE o.user = :user")
	long countNumberOfOrder(@Param("user") User user);

	@Query("SELECT new poly.agile.webapp.dto.OrderLineDTO"
			+ "(ol.product.id, ol.product.thumbnail, ol.product.name, ol.quantity, ol.product.price) "
			+ "FROM Order o INNER JOIN OrderLine ol ON o = ol.order WHERE o.id = :id")
	List<OrderLineDTO> findOrderLinesByOrderId(Integer id);

	@Query("SELECT new poly.agile.webapp.dto.OrderDTO"
			+ "(o.id, o.customerName, o.phoneNumber, o.address, o.createdTime, o.status.name) "
			+ "FROM Order o WHERE o.customerName like :search")
	Page<OrderDTO> getPages(@Param("search") String seach, Pageable pageable);

}
