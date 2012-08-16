package org.ideademo.piko.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.tapestry5.beaneditor.NonVisual;

/**
 * Edit Mode: Compact ("update"), Long Form ("Create"), Raw/Bean Editor ("edit")
 * This will preserve the use choice as to what type of input form will be used. 
 * The compact form will be the default, the long form may be useful for fully rendering labels, 
 * Microsoft IE users may want to choose the long form, since the browser is somewhat long in its tooth 
 * with respect to handling expanding text areas, labels and such: all elements are in linear blocks on the 
 * long forms - even MSIE can handle that...
 *   
 * @author Uday
 *
 */
@Entity
public class Mode 
{ //////////////////////////////////////////
	  //  Resevred indexing id 
	
  @Id @GeneratedValue @NonVisual
  private Long id;
  private String edit="update";

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getEdit() {
		return edit;
	}
	
	public void setEdit(String edit) {
		this.edit = edit;
	}
  
}
