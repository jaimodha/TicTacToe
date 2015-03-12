package ttt.service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import ttt.model.MultiplayerGame;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MultiSer {
private static final ObjectMapper objectMapper = new ObjectMapper();
	
	List<MultiplayerGame> mg;
	Queue<DeferredResult<String>> results;
	
		public MultiSer(){
		
	  mg = new ArrayList<MultiplayerGame>();
      results = new LinkedList<DeferredResult<String>>();
      
		}
      public List<MultiplayerGame> getMultiplayerGame()
      {
          return mg;
      }

      public void add( MultiplayerGame multigame )
      {
          mg.add( multigame );
          processDeferredResults();
      }

      public void remove( MultiplayerGame multigame)
      {
          mg.remove( multigame );
          
          processDeferredResults();
      }

      public void addResult( DeferredResult<String> result )
      {
          results.add( result );
      }

      public void processDeferredResults()
      {
          // convert username list to json
          String json = "";
          try
          {
              StringWriter sw = new StringWriter();
              objectMapper.writeValue( sw, mg );
              json = sw.toString();
          }
          catch( Exception e )
          {
              System.out.println(e);
          }

          // process queued results
          while( !results.isEmpty() )
          {
              DeferredResult<String> result = results.remove();
              result.setResult( json );
          }
      }
}
