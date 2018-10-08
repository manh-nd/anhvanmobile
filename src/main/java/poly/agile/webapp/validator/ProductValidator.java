package poly.agile.webapp.validator;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import poly.agile.webapp.model.Product;
import poly.agile.webapp.model.ProductSpec;
import poly.agile.webapp.model.ProductSpecDetail;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Product.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;

		String productName = product.getName();
		Integer price = product.getPrice();
		Integer qtyInStock = product.getQtyInStock();

		if (productName == null || productName.isEmpty())
			errors.rejectValue("name", "product.name", "Vui lòng nhập vào tên sản phẩm!");

		if (price == null)
			errors.rejectValue("price", "product.price", "Vui lòng nhập vào giá sản phẩm!");

		if (qtyInStock == null)
			errors.rejectValue("qtyInStock", "product.qtyInStock", "Vui lòng nhập vào số lượng sản phẩm!");

		List<ProductSpec> productSpecs = product.getProductSpecs();

		productSpecs.forEach(s -> {
			int sIndex = productSpecs.indexOf(s);
			List<ProductSpecDetail> details = s.getProductSpecDetails();

			details.forEach(sd -> {
				int sdIndex = s.getProductSpecDetails().indexOf(sd);
				String specDetailName = sd.getName();
				if (specDetailName == null || specDetailName.isEmpty())
					errors.rejectValue(String.format("productSpecs[%d].productSpecDetails[%d].name", sIndex, sdIndex),
							String.format("product.productSpecs[%d].productSpecDetails[%d].name", sIndex, sdIndex),
							"Vui lòng nhập vào tên!");
				String value = sd.getValue();
				if (value == null || value.isEmpty())
					errors.rejectValue(String.format("productSpecs[%d].productSpecDetails[%d].name", sIndex, sdIndex),
							String.format("product.productSpecs[%d].productSpecDetails[%d].name", sIndex, sdIndex),
							"Vui lòng nhập vào tên!");
			});
		});
	}

}
