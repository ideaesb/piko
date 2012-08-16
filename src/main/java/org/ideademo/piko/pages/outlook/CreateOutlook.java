package org.ideademo.piko.pages.outlook;

import org.hibernate.Session;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import org.apache.tapestry5.ioc.annotations.Inject;

import org.ideademo.piko.entities.Outlook;
import org.ideademo.piko.pages.Outlooks;


public class CreateOutlook
{
	
  @Inject
  private Session session;
  
  @PageActivationContext 
  @Property
  private Outlook entity;
  
  @InjectPage
  private Outlooks list;
  
  
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
  
  void onPrepareForRender()  {if(this.entity == null){this.entity = new Outlook();}}
  void onPrepareForSubmit()  {if(this.entity == null){this.entity = new Outlook();}}
}
