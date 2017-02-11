package listelecture;
import org.gephi.graph.api.*;
import org.gephi.graph.api.Edge;
import org.gephi.project.api.*;
import java.util.*;
/* la classe  DirectedGraph herite de la classe Graph,*/
public class grapheBiblio extends DirectedGraph
{
	Hashtable<Integer,Integer> NoeudScore;
	Node racine;
	void initialiserScore(){
		int score=0;
		NoeudScore = new Hashtable<Integer, Integer>();
		for(Node n : getNodes()) {//getNodes() retourne une liste des noeuds
			NoeudScore.put(n.getId(), score);
		}
	}
	void attribuerScoreRacine(){
		for(Node n : getNodes())		
		{
			int x=getOutDegree(n);
			if (x==0)//noeud racine 
			{	
				racine=n;
				int score=1000;
				NoeudScore.put(n.getId(), score);
			}
		}
	
		
	}
	
	void attribuerScoreSelonNiveau(Node n,int niveau){
		
		int score=NoeudScore.get(n.getId()) - niveau;
		NoeudScore.put(n.getId(), score);//mise a jour du score selon le niveau
		NodeIterable ListeFille=getPredecessors(Node node);
		for(Node n : ListeFille)
		{
			niveau=niveau+1;
			attribuerScoreSelonNiveau(n,niveau);	
			
		}
	
	}
	List genererListeLecture (){
		initialiserScore();
		attribuerScoreRacine();
		attribuerScoreSelonNiveau(racine,0);
    	List<Integer> listeLectureOrdonneId = new List<Integer>(); 

		int count=NoeudScore.size();
		
		
		
		Integer  TableauScoreTrie[]=new Integer[count];
		    int i=0;
		    for (Integer keyId : NoeudScore.keySet())//remplissage tableau
		    {
		    	TableauScoreTrie[i]=NoeudScore.get(keyId);
		        i++;
		    }
		    Arrays.sort(TableauScoreTrie);
		    //outer:for(int j=TableauScoreTrie.length-1;j>=0;j--)
		    for(int j=TableauScoreTrie.length-1;j>=0;j--)
		    {
		        for(Integer keyId : NoeudScore.keySet())//retourne les clés(les id) de hachtable 

		        if(NoeudScore.get(keyId)==TableauScoreTrie[j])
		        {
		        	listeLectureOrdonneId.add(keyId);
		            System.out.println(keyId+" "+TableauScoreTrie[j]);
		           
		        }
		    }

            return(listeLectureOrdonneId);

		}

	public static void main(){
		
		grapheBiblio notreGraphe= ConstruireGraphe();
		
		//Appel de la fonction construction graphe. 
		
		List listeArticle=notreGraphe.genererListeLecture ();

		
		
		
		
	}
	
	
	
}
