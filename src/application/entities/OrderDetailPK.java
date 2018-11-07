package application.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class OrderDetailPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 489305114136505166L;
	private String motorbike;
	private String order;
	private String orderDetailID;
	public OrderDetailPK() {
		super();
	}
	
	public OrderDetailPK(String motorbike, String order,String orderDetailID) {
		super();
		this.motorbike = motorbike;
		this.order = order;
		this.orderDetailID = orderDetailID;
	}



	public String getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(String orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	public String getMotorbike() {
		return motorbike;
	}
	
	public void setMotorbike(String motorbike) {
		this.motorbike = motorbike;
	}
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((motorbike == null) ? 0 : motorbike.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((orderDetailID == null) ? 0 : orderDetailID.hashCode());
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
		OrderDetailPK other = (OrderDetailPK) obj;
		if (motorbike == null) {
			if (other.motorbike != null)
				return false;
		} else if (!motorbike.equals(other.motorbike))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (orderDetailID == null) {
			if (other.orderDetailID != null)
				return false;
		} else if (!orderDetailID.equals(other.orderDetailID))
			return false;
		return true;
	}

	

	

	



	
	

}
