package org.ideademo.piko.pages.climatology;

import org.hibernate.Session;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import org.apache.tapestry5.ioc.annotations.Inject;

import org.ideademo.piko.entities.Climatology;
import org.ideademo.piko.pages.Climatologies;


public class CreateClimatology
{
	
  @Inject
  private Session session;
  
  @PageActivationContext 
  @Property
  private Climatology entity;
  
  @InjectPage
  private Climatologies list;
  
  
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
  
  void onPrepareForRender()  {if(this.entity == null){this.entity = new Climatology();}}
  void onPrepareForSubmit()  {if(this.entity == null){this.entity = new Climatology();}}
}
