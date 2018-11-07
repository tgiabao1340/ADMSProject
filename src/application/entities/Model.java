package application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Model {
	@Id
	@Column(name="ModelID")
	private String modelID;
	@Column(name="Name",columnDefinition="nvarchar(50)")
	private String name;
	public Model(String modelID, String name) {
		super();
		this.modelID = modelID;
		this.name = name;
	}
	public Model() {
		super();
	}
	public String getModelID() {
		return modelID;
	}
	public void setModelID(String modelID) {
		this.modelID = modelID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((modelID == null) ? 0 : modelID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		if (modelID == null) {
			if (other.modelID != null)
				return false;
		} else if (!modelID.equals(other.modelID))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Model [modelID=" + modelID + ", name=" + name + "]";
	}
	
	
}
