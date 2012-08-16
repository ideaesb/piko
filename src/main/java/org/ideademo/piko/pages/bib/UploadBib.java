package org.ideademo.piko.pages.bib;

import java.io.File;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.tapestry5.PersistenceConstants;

import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

import org.apache.tapestry5.upload.services.UploadedFile;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Value;

import org.hibernate.Session;

import org.ideademo.piko.entities.Bib;

public class UploadBib 
{
	
    @Inject
	private Session session;
    
    @Property
    private UploadedFile file;
    
    @PageActivationContext 
    @Property
    private Bib bib;
    
	@Property
	@Inject @Value(value="${worksheets.dir}")
	private String worksheetDirectory;

    @SuppressWarnings("unused")
    @Persist(PersistenceConstants.FLASH)
    @Property
    private String message;

    @CommitAfter
    public void onSuccess()
    {
      File copied = new File(worksheetDirectory + file.getFileName());
      file.write(copied);
      bib.setWorksheet(file.getFileName());
      bib.setWorksheetExists(true);
      session.saveOrUpdate(bib);
      message = "Done.  Uploaded : " + file.getFileName() + " in BIB: " + bib.getName(); 
    }
    

    Object onUploadException(FileUploadException ex)
    {
       message = "Upload exception: " + ex.getMessage();
       return this;
    }
}
