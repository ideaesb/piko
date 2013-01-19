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

import org.ideademo.piko.entities.Scenario;
import org.ideademo.piko.entities.Mode;

import org.apache.log4j.Logger;


public class Scenarios
{

  private static Logger logger = Logger.getLogger(Scenarios.class);
  /////////////////////////////
  //  Drives QBE Search
  @Persist (PersistenceConstants.FLASH)
  private Scenario example;
  
  
  //////////////////////////////
  // Used in rendering Grid Row
  @SuppressWarnings("unused")
  @Property 
  private Scenario row;

    
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
  public List<Scenario> getList()
  {
	  
    if(example != null)
    {
       Example ex = Example.create(example).excludeFalse().ignoreCase().enableLike(MatchMode.ANYWHERE);
       return session.createCriteria(Scenario.class).add(ex).list();
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
      
       QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity( Scenario.class ).get();
       org.apache.lucene.search.Query luceneQuery = qb
			    .keyword()
			    .onFields("name")
			    .matching(searchText)
			    .createQuery();
      	  
       return fullTextSession.createFullTextQuery(luceneQuery, Scenario.class).list();
    }
    else
    {
      // default - unfiltered - all entitites list 
      return session.createCriteria(Scenario.class).list();
    }
  }
  
  /**
   * just edit mode - nothing to do with entity
   */
	void onPrepareForRender()  
	{
		// figure out edit mode: compact, long or raw
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
    Scenario entity = (Scenario) session.get(Scenario.class, id);
    session.delete(entity);
    logger.info("Deleted " + entity.getName());
  }

  void onActionFromShowAll()
  {
    this.searchText = "";
    this.example = null;
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

  public void setExample(Scenario x) 
  {
    this.example = x;
  }


}
