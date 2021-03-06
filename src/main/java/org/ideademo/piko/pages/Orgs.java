package org.ideademo.piko.pages;

import java.util.List;

import org.apache.tapestry5.PersistenceConstants;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Persist;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.hibernate.HibernateSessionManager;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Value;


import org.hibernate.Session;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import org.ideademo.piko.entities.Mode;
import org.ideademo.piko.entities.Org;

import org.apache.log4j.Logger;


public class Orgs 
{
	 
  private static Logger logger = Logger.getLogger(Orgs.class);

  
  /////////////////////////////
  //  Drives QBE Search
  @Persist (PersistenceConstants.FLASH)
  private Org orgExample;
  
  
  //////////////////////////////
  // Used in rendering Grid Row
  @SuppressWarnings("unused")
  @Property 
  private Org orgRow;

    
  @Property
  @Persist (PersistenceConstants.FLASH)
  private String searchText;

  @Inject
  private Session session;
  
  @Inject
  private HibernateSessionManager sessionManager;

  @SuppressWarnings("unused")
  @Property
  private String editMode = "update";

  @SuppressWarnings("unused")
  @Property
  @Inject @Value(value="${worksheets.url}")
  private String url;

  
  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  //  Entity List generator - QBE, Text Search or Show All 
  //

  @SuppressWarnings("unchecked")
  public List<Org> getOrgs()
  {
	  
    if(orgExample != null)
    {
       Example example = Example.create(orgExample).excludeFalse().ignoreCase().enableLike(MatchMode.ANYWHERE);
       return session.createCriteria(Org.class).add(example).list();
    }
    else if (searchText != null && searchText.trim().length() > 0)
    {
      FullTextSession fullTextSession = Search.getFullTextSession(sessionManager.getSession());  
      try
      {
        fullTextSession.createIndexer().startAndWait();
       }
       catch (java.lang.InterruptedException e)
       {
         logger.warn("Lucene Indexing was interrupted by something " + e);
       }
      
       QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity( Org.class ).get();
       org.apache.lucene.search.Query luceneQuery = qb
			    .keyword()
			    .onFields("name")
			    .matching(searchText)
			    .createQuery();
      	  
       return fullTextSession.createFullTextQuery(luceneQuery, Org.class).list();
    }
    else
    {
      // default - unfiltered - all entitites list 
      return session.createCriteria(Org.class).list();
    }
  }
  
	void onPrepareForRender()  
	{
		Mode mode = new Mode();
		@SuppressWarnings("unchecked")
		java.util.List <Mode> list = session.createCriteria(Mode.class).list();
	    if (list != null && list.size() > 0) mode = (Mode) list.get(list.size() - 1);
		if(mode == null)
		{
			// nothing to do ?
			this.editMode = "update";
		}
		else
		{
			this.editMode = mode.getEdit();
		}
	}
  
  ///////////////////////////////////////////////////////////////
  //  Action Event Handlers 
  //
  
  @CommitAfter
  void onActionFromDelete(long id)
  {
    Org o = (Org) session.get(Org.class, id);
    session.delete(o);
    logger.info("Deleted " + o.getName());
  }

  void onActionFromShowAll()
  {
    this.searchText = "";
    this.orgExample = null;
  }

  Object onSelectedFromSearch() 
  {
    return null; 
  }

  Object onSelectedFromClear() 
  {
    onActionFromShowAll(); 
    return null; 
  }


  ////////////////////////////////////////////////
  //  QBE Setter 
  //  

  public void setOrgExample(Org orgExample) 
  {
    this.orgExample = orgExample;
  }

}