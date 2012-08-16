package org.ideademo.piko.components;

import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.ioc.annotations.Inject;

@Import(stylesheet = "context:layout/3col.css")
public class Dense 
{
	  private String pageNameBodyStyle="Index";
	  
	  @Inject
	  private ComponentResources resources;
	  
	  public String getPageNameBodyStyle()
	  {
		  pageNameBodyStyle = resources.getPageName();
	    
	    
	    // intercept only if contains slash high-level pages pass through 
	    if (StringUtils.contains(pageNameBodyStyle,'/'))
	    {
	      // hard-wired brute-force
	      if (StringUtils.containsIgnoreCase(pageNameBodyStyle, "need")) 
	      {
	    	  pageNameBodyStyle = "Needs";
	      }
	      else if (StringUtils.containsIgnoreCase(pageNameBodyStyle, "dap"))
	      {
	    	  pageNameBodyStyle = "Daps";
	      }
	      else if (StringUtils.containsIgnoreCase(pageNameBodyStyle, "paw"))
	      {
	    	  pageNameBodyStyle = "Paws";
	      }
	      else if (StringUtils.containsIgnoreCase(pageNameBodyStyle, "org"))
	      {
	    	  pageNameBodyStyle = "Orgs";
	      }
	      else if (StringUtils.containsIgnoreCase(pageNameBodyStyle, "bib"))
	      {
	    	  pageNameBodyStyle = "Bibs";
	      }
	      else if (StringUtils.containsIgnoreCase(pageNameBodyStyle, "asmt"))
	      {
	    	  pageNameBodyStyle = "Asmts";
	      }
	      else if (StringUtils.containsIgnoreCase(pageNameBodyStyle, "poc"))
	      {
	    	  pageNameBodyStyle = "Pocs";
	      }
	    }
	    
	    return pageNameBodyStyle;
	  }
	  
	  public void setPageNameBodyStyle(String str)
	  {
		 this.pageNameBodyStyle = str; 
	  }
	
	
}
