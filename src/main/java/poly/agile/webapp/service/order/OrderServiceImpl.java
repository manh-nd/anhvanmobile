package poly.agile.webapp.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import poly.agile.webapp.dto.OrderDTO;
import poly.agile.webapp.model.Order;
import poly.agile.webapp.model.OrderStatus;
import poly.agile.webapp.repository.OrderRepository;
import poly.agile.webapp.repository.OrderStatusRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderStatusRepository orderStatusRepository;
	

	@Override
	public Order create(Order o) {
		return orderRepository.save(o);
	}

	@Override
	public Order update(Order o) {
		return orderRepository.save(o);
	}

	@Override
	public boolean remove(Order o) {
		try {
			orderRepository.delete(o);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Order findById(Integer id) {
		return orderRepository.getOne(id);
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public OrderStatus findOrderStatusById(Integer id) {
		return orderStatusRepository.getOne(id);
	}

	@Override
	public Page<OrderDTO> getPages(int page, int size) {
		if (page < 1)
			page = 1;
		if (size < 5)
			size = 5;
		return orderRepository.getPages(PageRequest.of(page - 1, size));
	}

	@Override
	public List<OrderStatus> findAllOrderStatus() {
		return orderStatusRepository.findAll();
	}

}
