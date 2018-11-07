package application.daos;

import java.util.ArrayList;
import java.util.List;

import application.entities.Motorbike;

public class MotorbikeDAO extends GeneralCRUD<Motorbike> {
	public List<Motorbike> findbyType(String type) {
		List<Motorbike> list = this.getAll(Motorbike.class);
		List<Motorbike> listbyType = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Motorbike mb = list.get(i);
			if (mb.getType().equalsIgnoreCase(type)) {
				listbyType.add(mb);
			}
		}
		return listbyType;
	}

	public List<Motorbike> findbyTypeWSup(String type, String supname) {
		List<Motorbike> list = this.getAll(Motorbike.class);
		List<Motorbike> listbyType = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Motorbike mb = list.get(i);
			if (mb.getType().equalsIgnoreCase(type) && mb.getSupplier().getSupplierName().equalsIgnoreCase(supname)) {
				listbyType.add(mb);
			}
		}
		return listbyType;
	}

	public List<Motorbike> findbyName(String name) {
		List<Motorbike> list = this.getAll(Motorbike.class);
		List<Motorbike> listbyName = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Motorbike mb = list.get(i);
			if (mb.getProductName().toUpperCase().contains(name.toUpperCase())) {
				listbyName.add(mb);
			}
		}
		return listbyName;
	}
}
