package org.ideademo.piko.pages.asmt;

import org.hibernate.Session;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import org.apache.tapestry5.ioc.annotations.Inject;

import org.ideademo.piko.entities.Asmt;
import org.ideademo.piko.pages.Asmts;


public class UpdateAsmt
{
	
  @Inject
  private Session session;
  
  @PageActivationContext 
  @Property
  private Asmt entity;
  
  @InjectPage
  private Asmts list;
  
  
  Object onSelectedFromSearch() 
  {
	list.setExample(entity);
    return list;
  }
  
  
  @CommitAfter
  Object onSuccess()
  {
   session.saveOrUpdate(entity);
   return list;
  }
  
  void onPrepareForRender()  {if(this.entity == null){this.entity = new Asmt();}}
  void onPrepareForSubmit()  {if(this.entity == null){this.entity = new Asmt();}}
}
