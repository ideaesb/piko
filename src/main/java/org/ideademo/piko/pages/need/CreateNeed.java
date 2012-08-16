package org.ideademo.piko.pages.need;

import org.hibernate.Session;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import org.apache.tapestry5.ioc.annotations.Inject;

import org.ideademo.piko.entities.Need;
import org.ideademo.piko.pages.Needs;


public class CreateNeed
{
	
  @Inject
  private Session session;
  
  @PageActivationContext 
  @Property
  private Need need;
  
  @InjectPage
  private Needs needs;
  
  
  Object onSelectedFromSearch() 
  {
	needs.setNeedExample(need);
    return needs;
  }
  
  
  @CommitAfter
  Object onSuccess()
  {
   session.saveOrUpdate(need);
   return needs;
  }
  
  void onPrepareForRender()  {if(this.need == null){this.need = new Need();}}
  void onPrepareForSubmit()  {if(this.need == null){this.need = new Need();}}
}
