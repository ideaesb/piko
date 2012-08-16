package org.ideademo.piko.pages.bib;

import org.hibernate.Session;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import org.apache.tapestry5.ioc.annotations.Inject;

import org.ideademo.piko.entities.Bib;
import org.ideademo.piko.pages.Bibs;


public class UpdateBib
{
	
  @Inject
  private Session session;
  
  @PageActivationContext 
  @Property
  private Bib bib;
  
  @InjectPage
  private Bibs bibs;
  
  
  Object onSelectedFromSearch() 
  {
	bibs.setBibExample(bib);
    return bibs;
  }
  
  
  @CommitAfter
  Object onSuccess()
  {
   session.saveOrUpdate(bib);
   return bibs;
  }
  
  void onPrepareForRender()  {if(this.bib == null){this.bib = new Bib();}}
  void onPrepareForSubmit()  {if(this.bib == null){this.bib = new Bib();}}
}
