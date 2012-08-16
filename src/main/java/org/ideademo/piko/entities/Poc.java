package org.ideademo.piko.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity @Indexed
public class Poc {

	
	//////////////////////////////////////////
	//Reserved indexing id 
	
	@Id @GeneratedValue @DocumentId @NonVisual
	private Long id;
	
	
	//////////////////////////////////////////////
	//String fields (being a keyword for Lucene)
	//
	
	@Field @Column (length=1024)
	private String name="";
	
	@Field @Column (length=2048)
	private String position="";

	@Field @Column (length=2048)
	private String organization="";

	@Field @Column (length=2048)
	private String role="";

	@Field
	private String email="";
	@Field
	private String phone="";
	@Field
	private String mobile="";
	@Field
	private String fax="";
	
	@Field 
	private String url="";
	
	@Field @Column (length=2048)
	private String address="";
	
	@Field @Column (length=4096)
	private String keywords="";
	
	@Field @Column (length=4096)
	private String description="";
	
	@Field 
	private String worksheet="";
	
	
	////////////////////////////////////////////////
	//  roles
	//
	
	private boolean groupPoc = false;
	private boolean individual = false;
	
	private boolean federal = false;
	private boolean state = false;
	private boolean local = false;
	private boolean interagency = false;
	private boolean academic = false;
	private boolean ngo = false;
	private boolean contractor = false;
	private boolean consultant = false;
	private boolean principalInvestigator = false;
	private boolean pacisChair = false; 
	private boolean otherContactType = false;
	
	
    /////////////////////////////////////////////
	//  internal flags
	  
	private boolean worksheetExists = false;
	
	
	
	
	///////////////////
	// getter, setters
	
	public boolean isIndividual() {
		return individual;
	}
	public void setIndividual(boolean individual) {
		this.individual = individual;
	}
	public boolean isGroupPoc() {
		return groupPoc;
	}
	public void setGroupPoc(boolean groupPoc) {
		this.groupPoc = groupPoc;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getKeywords() {
		return keywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getWorksheet() {
		return worksheet;
	}
	public void setWorksheet(String worksheet) {
		this.worksheet = worksheet;
	}
	public boolean isFederal() {
		return federal;
	}
	public void setFederal(boolean federal) {
		this.federal = federal;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public boolean isLocal() {
		return local;
	}
	public void setLocal(boolean local) {
		this.local = local;
	}
	public boolean isInteragency() {
		return interagency;
	}
	public void setInteragency(boolean interagency) {
		this.interagency = interagency;
	}
	public boolean isAcademic() {
		return academic;
	}
	public void setAcademic(boolean academic) {
		this.academic = academic;
	}
	public boolean isNgo() {
		return ngo;
	}
	public void setNgo(boolean ngo) {
		this.ngo = ngo;
	}
	public boolean isContractor() {
		return contractor;
	}
	public void setContractor(boolean contractor) {
		this.contractor = contractor;
	}
	public boolean isConsultant() {
		return consultant;
	}
	public void setConsultant(boolean consultant) {
		this.consultant = consultant;
	}
	public boolean isPrincipalInvestigator() {
		return principalInvestigator;
	}
	public void setPrincipalInvestigator(boolean principalInvestigator) {
		this.principalInvestigator = principalInvestigator;
	}
	public boolean isPacisChair() {
		return pacisChair;
	}
	public void setPacisChair(boolean pacisChair) {
		this.pacisChair = pacisChair;
	}
	public boolean isOtherContactType() {
		return otherContactType;
	}
	public void setOtherContactType(boolean otherContactType) {
		this.otherContactType = otherContactType;
	}
	public boolean isWorksheetExists() {
		return worksheetExists;
	}
	public void setWorksheetExists(boolean worksheetExists) {
		this.worksheetExists = worksheetExists;
	}


}
