package org.ideademo.piko.pages.org;

import org.hibernate.Session;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import org.apache.tapestry5.ioc.annotations.Inject;

import org.ideademo.piko.entities.Org;
import org.ideademo.piko.pages.Orgs;


public class EditOrg
{
	
  @Inject
  private Session session;
  
  @PageActivationContext 
  @Property
  private Org org;
  
  @InjectPage
  private Orgs orgs;
  
  
  Object onSelectedFromSearch() 
  {
	orgs.setOrgExample(org);
    return orgs;
  }
  
  
  @CommitAfter
  Object onSuccess()
  {
   session.saveOrUpdate(org);
   return orgs;
  }
  
  void onPrepareForRender()  {if(this.org == null){this.org = new Org();}}
  void onPrepareForSubmit()  {if(this.org == null){this.org = new Org();}}
}
