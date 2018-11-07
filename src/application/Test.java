package application;

import java.time.LocalDate;
import java.util.Arrays;

import application.daos.AccountDAO;
import application.daos.CustomerDAO;
import application.daos.EmployeeDAO;
import application.daos.ModelDAO;
import application.daos.MotorbikeDAO;
import application.daos.OrderDAO;
import application.daos.OrderDetailDAO;
import application.daos.SupplierDAO;
import application.entities.Account;
import application.entities.Customer;
import application.entities.Employee;
import application.entities.Model;
import application.entities.Motorbike;
import application.entities.Order;
import application.entities.OrderDetail;
import application.entities.Supplier;

public class Test {
	public static void main(String[] args) {

		Account account = new Account("NV001", "123", LocalDate.now());
		Account account1 = new Account("NV002", "123", LocalDate.now());
		Account account2 = new Account("KT001", "123", LocalDate.now());
		Account account3 = new Account("QL001", "123", LocalDate.now());

		Employee e = new Employee("Qui", "Phan Hoàng", "14 Phạm Văn Đồng, phường 7, Quận Gò Vấp, TP.HCM", true,
				LocalDate.of(1998, 10, 10), "0968567837", "NV Bán hàng", account);
		Employee e1 = new Employee("Hải", "Nguyễn Thanh", "99 Hoàng Hoa Thám, phường 11, Quận Bình Thạnh, TP.HCM", true,
				LocalDate.of(1995, 12, 10), "0968567837", "NV Bán hàng", account1);
		Employee e2 = new Employee("Binh", "Trần Thanh", "234 Lê Đức Thọ, phường 15, Quận Gò Vấp, TP.HCM", true,
				LocalDate.of(1997, 7, 18), "0968567837", "NV Kỹ thuật", account2);
		Employee e3 = new Employee("Thằng", "Đào Minh", "112 Lê Đại Thành, phường 7 Quận 10, TP.HCM", true,
				LocalDate.of(1987, 2, 21), "0968567837", "NV Quản lý", account3);

		Customer customer = new Customer("An", "Nguyễn Văn", "221 Trần Hưng Đạo, phường Phạm Ngũ Lão, Quận 1, Tp.HCM",
				true, LocalDate.of(1988, 4, 28), "096756787", LocalDate.now(), "2625345321",
				LocalDate.of(2008, 10, 10));
		Customer customer1 = new Customer("Đạt", "Nguyễn Tấn",
				"223 Nguyễn Trãi, phường Nguyễn Cư Trinh, Quận 1, Tp.HCM", true, LocalDate.of(1998, 4, 1), "0879657423",
				LocalDate.now(), "262534445", LocalDate.of(2009, 9, 23));
		Customer customer2 = new Customer("Thúy", "Trần Ngọc", "57/6 Trần Văn Đang, phường 9, Quận 3, Tp.HCM", false,
				LocalDate.of(1999, 5, 5), "0897678954", LocalDate.now(), "262344664", LocalDate.of(2007, 1, 27));
		Customer customer3 = new Customer("Thi", "Trần Thị Tấn", "159/9/3 Phạm Thế Hiển, phường 8, Quận 8, Tp.HCM",
				false, LocalDate.of(1986, 6, 10), "0989767545", LocalDate.now(), "26256765", LocalDate.of(2008, 3, 1));
		Customer customer4 = new Customer("Ngọc", "Nguyễn Thị Bích",
				"159 Xa Lộ Hà Nội, phường Thảo Điền, Quận 2, Tp.HCM", false, LocalDate.of(1998, 8, 26), "09034534532",
				LocalDate.now(), "262554667", LocalDate.of(2005, 7, 23));
		Customer customer5 = new Customer("Bảo", "Nguyễn Thế", "600/6/3, phường 25, Quận Bình Thạnh, Tp.HCM", true,
				LocalDate.of(1998, 4, 18), "0917645778", LocalDate.now(), "262534523", LocalDate.of(2007, 4, 21));
		Customer customer6 = new Customer("Thái", "Trương Quốc", "D3/N4 Khu Dân cư Bắc Hải, phường 13, Quận 3, Tp.HCM",
				true, LocalDate.of(1993, 4, 28), "0902343212", LocalDate.now(), "2625344343",
				LocalDate.of(20012, 4, 11));

		Supplier supplier = new Supplier("NCC001", "Thái Lan", "HONDA", "221 Nguyễn Văn Linh, phường 4, Quận 7",
				"0839869011", "324532");
		Supplier supplier2 = new Supplier("NCC002", "Ấn Độ", "YAMAHA", "12 Quốc Hương, phường Thảo Điền, Quận 2",
				"0839862222", "324235");
		Supplier supplier3 = new Supplier("NCC003", "Đài Loan", "SYM", "138-140 Hai Bà Trưng, phường Bến Nghé, Quận 1",
				"0839864181", "324352");
		Supplier supplier4 = new Supplier("NCC004", "Nhật Bản", "SUZUKI", "12 Hoàng Diệu, phường 4, Quận 4",
				"0839860909", "324325");

		Model model = new Model("MD001", "AIR BLADE");
		Model model1 = new Model("MD002", "SH");
		Model model2 = new Model("MD003", "Hayante");
		Model model3 = new Model("MD004", "Angela");
		Model model4 = new Model("MD005", "Dream");
		Model model5 = new Model("MD006", "Cub");
		Model model6 = new Model("MD006", "Wave");
		Model model7 = new Model("MD007", "Sirius");
		Model model8 = new Model("MD008", "Lead");
		Model model9 = new Model("MD008", "Vison");

		Motorbike motorbike = new Motorbike("Honda Air Blade 2017", "Mới", 24, 40000000, 100, null, null, 125.0,
				"Xanh, Trắng, Đỏ,Đen", 2017, "Tay ga");
		Motorbike motorbike1 = new Motorbike("Honda Lead 2 2017", "Mới", 24, 36000000, 100, model8, supplier, 125.0,
				"Xanh, Trắng, Đỏ", 2017, "Tay ga");
		Motorbike motorbike2 = new Motorbike("Honda Vison 2", "Mới", 24, 30000000, 100, model9, supplier, 110.0,
				"Xanh, Trắng, Đỏ", 2017, "Tay ga");
		Motorbike motorbike3 = new Motorbike("Honda Dream 2017", "Mới", 24, 16000000, 100, model4, supplier, 125.0,
				"Xanh, Trắng, Đỏ", 2017, "Xe số");
		Motorbike motorbike4 = new Motorbike("Sym Angela 110cc", "Mới", 24, 18000000, 100, model3, supplier3, 110.0,
				"Xanh, Trắng, Đỏ,Vàng", 2017, "Xe số");
		Motorbike motorbike5 = new Motorbike("Yamaha Sirius FI", "Mới", 24, 23000000, 100, model7, supplier2, 110.0,
				"Xanh, Trắng, Đỏ", 2017, "Xe số");
		Motorbike motorbike6 = new Motorbike("Honda SH-mode 125i", "Mới", 24, 60000000, 100, model1, supplier, 125.0,
				"Xanh, Trắng, Đỏ", 2017, "Tay ga");
		Motorbike motorbike7 = new Motorbike("Honda SH  150i", "Mới", 24, 80000000, 100, model1, supplier, 150.0,
				"Xanh, Trắng, Đỏ", 2017, "Tay ga");
		Motorbike motorbike8 = new Motorbike("Suzuki Hayante", "Mới", 24, 29000000, 100, model2, supplier4, 125.0,
				"Xanh, Trắng, Đỏ", 2017, "Tay ga");

		Order order = new Order("OR001", LocalDate.now(), e, customer, "");
		Order order1 = new Order(LocalDate.now(), null, null, "");
		Order order2 = new Order("OR003", LocalDate.now(), e1, customer2, "");
		Order order3 = new Order("OR004", LocalDate.now(), e1, customer, "");
		Order order4 = new Order("OR005", LocalDate.now(), e1, customer, "");
		Order order5 = new Order("OR006", LocalDate.now(), e2, customer, "");
		Order order6 = new Order("OR007", LocalDate.now(), e1, customer, "");

		OrderDetail detail = new OrderDetail(order1, motorbike, 0.15, motorbike.getUnitPrice(), 1, 0.1, "Trắng");
		OrderDetail detail1 = new OrderDetail(order1, motorbike, 0.15, motorbike.getUnitPrice(), 1, 0.1, "Xanh");
		OrderDetail detail2 = new OrderDetail(order2, motorbike2, 0.15, motorbike.getUnitPrice(), 1, 0.1, "Đỏ");
		OrderDetail detail3 = new OrderDetail(order3, motorbike3, 0.15, motorbike.getUnitPrice(), 1, 0.1, "Đen");
		OrderDetail detail4 = new OrderDetail(order4, motorbike4, 0.15, motorbike.getUnitPrice(), 1, 0.1, "Vàng");
		OrderDetail detail5 = new OrderDetail(order5, motorbike5, 0.15, motorbike.getUnitPrice(), 1, 0.1, "Trắng");
		OrderDetail detail6 = new OrderDetail(order6, motorbike6, 0.15, motorbike.getUnitPrice(), 1, 0.1, "Đen");

		order.setListItems(Arrays.asList(detail));
		order1.setListItems(Arrays.asList(detail, detail1));
		order2.setListItems(Arrays.asList(detail2));
		order3.setListItems(Arrays.asList(detail3));
		order4.setListItems(Arrays.asList(detail4));
		order5.setListItems(Arrays.asList(detail5));
		order6.setListItems(Arrays.asList(detail6));

		AccountDAO accountDAO = new AccountDAO();
		accountDAO.save(account);
		accountDAO.save(account1);
		accountDAO.save(account2);
		accountDAO.save(account3);

		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.save(customer);
		customerDAO.save(customer1);
		customerDAO.save(customer2);
		customerDAO.save(customer3);
		customerDAO.save(customer4);
		customerDAO.save(customer5);
		customerDAO.save(customer6);

		ModelDAO modelDAO = new ModelDAO();
		modelDAO.save(model);
		modelDAO.save(model1);
		modelDAO.save(model2);
		modelDAO.save(model3);
		modelDAO.save(model4);
		modelDAO.save(model5);
		modelDAO.save(model6);
		modelDAO.save(model7);
		modelDAO.save(model8);
		modelDAO.save(model9);

		SupplierDAO supplierDAO = new SupplierDAO();
		supplierDAO.save(supplier);
		supplierDAO.save(supplier2);
		supplierDAO.save(supplier3);
		supplierDAO.save(supplier4);

		MotorbikeDAO motorbikeDAO = new MotorbikeDAO();
		motorbikeDAO.save(motorbike);
		motorbikeDAO.save(motorbike1);
		motorbikeDAO.save(motorbike2);
		motorbikeDAO.save(motorbike3);
		motorbikeDAO.save(motorbike4);
		motorbikeDAO.save(motorbike5);
		motorbikeDAO.save(motorbike6);
		motorbikeDAO.save(motorbike7);
		motorbikeDAO.save(motorbike8);

		EmployeeDAO dao2 = new EmployeeDAO();
		dao2.save(e);
		dao2.save(e1);
		dao2.save(e2);
		dao2.save(e3);
		OrderDAO dao = new OrderDAO();
		dao.save(order);
		dao.save(order1);
		dao.save(order2);
		dao.save(order3);
		dao.save(order4);
		dao.save(order5);
		dao.save(order6);

		OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
		order1.getListItems().forEach(x -> orderDetailDAO.save(x));
		orderDetailDAO.save(detail);
		orderDetailDAO.save(detail1);
		orderDetailDAO.save(detail2);
		orderDetailDAO.save(detail3);
		orderDetailDAO.save(detail4);
		orderDetailDAO.save(detail4);
		orderDetailDAO.save(detail6);

//		List<Order> list = dao.getAll(Order.class);
//		for (int i = 0; i < list.size(); i++) {
//			long totalRaw = list.get(i).getTotalRaw();
//			long totalVAT = list.get(i).getTotalVAT();
//			long totalReg = list.get(i).getTotalReg();
//			long totalSub = list.get(i).getSubTotal();
//			System.out.println(list.get(i));
//			System.out.printf("Gia ban : %s\nVAT : %s\nPhi truoc ba : %s\nThanh tien : %s\n", totalRaw, totalVAT,
//					totalReg, totalSub);
//	}
//		Order od1 = new Order(LocalDate.now(), new Employee(), new Customer(), "Blah Blah");
//		Order od2 = new Order(LocalDate.now(), new Employee(), new Customer(), "Blah Blah");
//		Order od3 = new Order(LocalDate.now(), new Employee(), new Customer(), "Blah Blah");
//		OrderDAO odDAO = new OrderDAO();
//		odDAO.save(od1);
//		odDAO.save(od2);
//		odDAO.save(od3);
//		List<Order> listod = odDAO.getAll(Order.class);
//		listod.forEach(x -> System.out.println(x));
//		OrderDAO odDAO = new OrderDAO();
//		List<Order> listod = new OrderDAO().getAll(Order.class);
//		for (int i = 0; i < listod.size(); i++) {
//			Order od = listod.get(i);
//			odDAO.delete(od);
//		}
//		MotorbikeDAO motorbikeDAO = new MotorbikeDAO();
//		OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
//		Motorbike motorbike = motorbikeDAO.getById(Motorbike.class, new String("XM0001"));
//		Order order = new Order(LocalDate.now(), null, null, "Testing");
//		OrderDetail orderDetail = new OrderDetail(order, motorbike, 0.1, 560000, 1, 0.1, "Xanh");
//		OrderDetail orderDetail2 = new OrderDetail(order, motorbike, 0.1, 560000, 1, 0.1, "Vàng");
//		orderDetail.setOrderDetailID("000");
//		orderDetail2.setOrderDetailID("001");
//		order.setListItems(Arrays.asList(orderDetail, orderDetail2));
//		// System.out.println(order);
//		OrderDAO orderDAO = new OrderDAO();
//		orderDAO.save(order);

		// orderDetailDAO.save(orderDetail);load_quantity(List<OrderDetail> list) {

	}
}