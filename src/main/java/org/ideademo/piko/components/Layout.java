package org.ideademo.piko.components;

import org.apache.commons.lang.StringUtils;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.ioc.annotations.Inject;


/**
 * Layout component for pages of application epiko.
 */
@Import(stylesheet = "context:layout/tabs.css")
public class Layout
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

