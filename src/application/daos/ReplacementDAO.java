package application.daos;

import java.util.ArrayList;
import java.util.List;

import application.entities.Model;
import application.entities.Replacement;
import application.entities.Supplier;

public class ReplacementDAO extends GeneralCRUD<Replacement> {
	public List<Replacement> findbySup(Supplier supplier) {
		List<Replacement> list = this.getAll(Replacement.class);
		List<Replacement> listbySup = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Replacement rp = list.get(i);
			if (rp.getSupplier().equals(supplier)) {
				listbySup.add(rp);
			}
		}
		return listbySup;
	}

	public List<Replacement> findbySupWModel(Supplier supplier, Model model) {
		List<Replacement> list = this.getAll(Replacement.class);
		List<Replacement> listbySupWModel = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Replacement rp = list.get(i);
			if (rp.getSupplier().equals(supplier) && rp.getModel().equals(model)) {
				listbySupWModel.add(rp);
			}
		}
		return listbySupWModel;
	}
}
