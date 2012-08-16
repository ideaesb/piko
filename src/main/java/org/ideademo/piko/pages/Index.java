package org.ideademo.piko.pages;

import org.hibernate.Session;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;

import org.apache.tapestry5.corelib.components.Zone;

import org.apache.tapestry5.ioc.annotations.Inject;

import org.apache.tapestry5.services.Request;

import org.apache.log4j.Logger;

import org.ideademo.piko.entities.Mode;

/**
 * Start page of application epiko.
 * All code herein is driving AJax for setting edit mode - 
 * @TODO move it to services 
 */
public class Index
{
	
    // edit modes are presented to the user in some meaningful choices---
	static final private String[] ALL_CHOICES = new String[] { "compact", "long", "raw"};
	private static Logger logger = Logger.getLogger(Index.class);
	
   //@PageActivationContext 
   @Property
   private Mode mode;	
   
   // Screen fields

	@Property
	@SuppressWarnings("unused")
	private String editChoice = "compact";

	@Property
	private String editMode = "update";

	@Property
	private String[] editChoices;
	
	// Generally useful bits and pieces

	@InjectComponent
	private Zone editZone;


	@Inject
	private Request request;	
	
	@Inject
    private Session session;

	void setupRender() 
	{
	  if (this.editChoices == null)
	  {
        this.editChoices= ALL_CHOICES;
	  }
	}
	
	@CommitAfter
	Object onValueChangedFromEditChoice(String choice) 
	{
	  this.editMode = "update";
      this.editChoices= ALL_CHOICES;
      
      if (choice == null)
	  {
		editMode = "update";
	  }
      else if (choice.equalsIgnoreCase("long"))
      {
    	editMode = "create";
      }
      else if (choice.equalsIgnoreCase("raw"))
      {
    	editMode = "edit";       
      }
      else
      {
    	editMode = "update";  
      }
	
      
      logger.info("Setting editMode to " + editMode);
      
      //////////////////////////////////////////////
      // put th value in the database, wipe it first
      
      //session.createQuery("delete org.ideademo.epiko.entities.Mode").executeUpdate();
      //Configuration cfg = new Configuration().addAnnotatedClass(Mode.class);
      
      //session.createQuery("create org.ideademo.epiko.entities.Mode").executeUpdate();
      
      
      if(this.mode == null)
      {
    	 logger.info("Mode was null................. ");
    	  this.mode = new Mode();
      }
      mode.setEdit(editMode);
      session.saveOrUpdate(mode);
      logger.info("Mode saved to database .. " + mode.getEdit());
      
	  return request.isXHR() ? editZone.getBody() : null;
		
	}
	
	void onPrepareForRender()  
	{
		@SuppressWarnings("unchecked")
		java.util.List <Mode> list = session.createCriteria(Mode.class).list();
	    if (list != null && list.size() > 0) this.mode = (Mode) list.get(list.size() - 1);
		if(this.mode == null)
		{
			this.mode = new Mode();
			mode.setEdit(this.editMode);
			logger.info("mode was null --- set to default = "  + this.editMode);
		}
		else
		{
		    logger.info("Mode retrieved from database ..id = " + mode.getId() + ",  pre-render mode = " + mode.getEdit());
		    
		    if (mode.getEdit().equalsIgnoreCase("edit"))
		    {
		    	this.editChoice = "raw";
		    	this.editMode = "edit";
		    }
		    else if (mode.getEdit().equalsIgnoreCase("create"))
		    {
		    	this.editChoice = "long";
		    	this.editMode = "create";
		    }
		    else
		    {
		    	this.editChoice = "compact";
		    	this.editMode = "update";
		    }
		}
    }
	void onPrepareForSubmit()  {if(this.mode == null){this.mode = new Mode();}}

}
