package application.daos;

import java.util.ArrayList;
import java.util.List;

import application.entities.Employee;
import application.entities.Model;
import application.entities.Motorbike;

public class ModelDAO extends GeneralCRUD<Model> {
	public Model findById(String id) {
		List<Model> list = this.getAll(Model.class);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getModelID().equalsIgnoreCase(id)) {
				return list.get(i);
			}
		}
		return null;
	}
}
