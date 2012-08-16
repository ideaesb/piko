package org.ideademo.piko.pages.paw;

import org.hibernate.Session;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import org.apache.tapestry5.ioc.annotations.Inject;

import org.ideademo.piko.entities.Paw;
import org.ideademo.piko.pages.Paws;


public class UpdatePaw
{
	
  @Inject
  private Session session;
  
  @PageActivationContext 
  @Property
  private Paw paw;
  
  @InjectPage
  private Paws paws;
  
  
  Object onSelectedFromSearch() 
  {
	paws.setPawExample(paw);
    return paws;
  }
  
  
  @CommitAfter
  Object onSuccess()
  {
   session.saveOrUpdate(paw);
   return paws;
  }
  
  void onPrepareForRender()  {if(this.paw == null){this.paw = new Paw();}}
  void onPrepareForSubmit()  {if(this.paw == null){this.paw = new Paw();}}
}
