package org.ideademo.piko.pages;

import java.io.File;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.tapestry5.PersistenceConstants;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

import org.apache.tapestry5.upload.services.UploadedFile;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Value;


public class FileUpload 
{
	
    @Property
    private UploadedFile file;
    
    
	@Property
	@Inject @Value(value="${worksheets.dir}")
	private String worksheetDirectory;

    @SuppressWarnings("unused")
	@Persist(PersistenceConstants.FLASH)
    @Property
    private String message;

    public void onSuccess()
    {
      File copied = new File(worksheetDirectory + file.getFileName());
      file.write(copied);
      message = "Done.  Uploaded : " + file.getFileName();
    }

    Object onUploadException(FileUploadException ex)
    {
       message = "Upload exception: " + ex.getMessage();
       return this;
    }
}
