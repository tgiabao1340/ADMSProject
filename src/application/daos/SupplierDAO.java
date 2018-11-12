package application.daos;

import java.util.List;

import application.entities.Model;
import application.entities.Supplier;

public class SupplierDAO extends GeneralCRUD<Supplier> {
	public Supplier findById(String id) {
		List<Supplier> list = this.getAll(Supplier.class);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSupplierID().equalsIgnoreCase(id)) {
				return list.get(i);
			}
		}
		return null;
	}
}
