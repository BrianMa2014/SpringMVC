package com.yintai.trx.domain;

public class Tenant {
    private int tenantId;
    private String tenantName;
    private String signingDate;
    private String contact;
    private String address;
    
	public Tenant(int tenantId, String tenantName, String signingDate,
			String contact, String address) {
		super();
		this.tenantId = tenantId;
		this.tenantName = tenantName;
		this.signingDate = signingDate;
		this.contact = contact;
		this.address = address;
	}
	public int getTenantId() {
		return tenantId;
	}
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public String getSigningDate() {
		return signingDate;
	}
	public void setSigningDate(String signingDate) {
		this.signingDate = signingDate;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    
}
