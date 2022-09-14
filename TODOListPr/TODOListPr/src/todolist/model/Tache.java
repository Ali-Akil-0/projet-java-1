package todolist.model;

public class Tache {
	public  String tacheCategorie  ; 
	public  String titreTache ;
	public int tacheId  ; 
	
	public int getTacheId() {
		return tacheId;
	}

	public void setTacheId(int tacheId) {
		this.tacheId = tacheId;
	}

	public Tache(String tacheCategorie, String TitreTache) {
		super();
		this.tacheCategorie = tacheCategorie;
		this.titreTache = TitreTache;
	}

	public Tache(String tacheCategorie, String TitreTache,int tacheId) {
		super();
		this.tacheCategorie = tacheCategorie;
		this.titreTache = TitreTache;
		this.tacheId=tacheId ; 
	}
	
	public String getTacheCategorie() {
		return tacheCategorie;
	}
	public void setTacheCategorie(String tacheCategorie) {
		this.tacheCategorie = tacheCategorie;
	}
	public String getTitreTache() {
		return titreTache;
	}
	public void setTitreTache(String TitreTache) {
		this.titreTache = TitreTache;
	}
	 

}
