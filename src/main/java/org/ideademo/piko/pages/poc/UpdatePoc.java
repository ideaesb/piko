package org.ideademo.piko.pages.poc;

import org.hibernate.Session;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import org.apache.tapestry5.ioc.annotations.Inject;

import org.ideademo.piko.entities.Poc;
import org.ideademo.piko.pages.Pocs;


public class UpdatePoc
{
	
  @Inject
  private Session session;
  
  @PageActivationContext 
  @Property
  private Poc entity;
  
  @InjectPage
  private Pocs list;
  
  
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
  
  void onPrepareForRender()  {if(this.entity == null){this.entity = new Poc();}}
  void onPrepareForSubmit()  {if(this.entity == null){this.entity = new Poc();}}
}
