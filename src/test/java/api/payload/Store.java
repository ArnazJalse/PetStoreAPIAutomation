package api.payload;

import java.util.TimeZone;

import org.joda.time.LocalDateTime;

public class Store {
	public int id;
    public int petId;
    public int quantity;
    public TimeZone shipDate;
    public String status;
    public boolean complete;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPetId() {
		return petId;
	}
	public void setPetId(int id) {
		this.petId = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public TimeZone getShipDate() {
		return shipDate;
	}
	public void setShipDate(TimeZone timeZone) {
		this.shipDate = timeZone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean getComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
	
    
    
}
