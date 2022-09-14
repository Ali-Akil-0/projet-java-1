package todolist.model;

public class Categorie {
	public String titreCategorie   ; 
	public int categorieId   ; 

	public Categorie(String titreCategorie, int categorieId) {
		super();
		this.titreCategorie = titreCategorie;
		this.categorieId = categorieId;
	}
	
	public Categorie(String titreCategorie) {
		super();
		this.titreCategorie = titreCategorie;
	}

	public String getTitreCategorie() {
		return titreCategorie;
	}
	public void setTitreCategorie(String titreCategorie) {
		this.titreCategorie = titreCategorie;
	}
	public int getCategorieId() {
		return categorieId;
	}
	public void setCategorieId(int categorieId) {
		this.categorieId = categorieId;
	}
	
}
