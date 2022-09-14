package todolist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import todolist.model.Categorie;
import todolist.model.Tache;

public class CategoryDAO {
	private String jdbcURL="jdbc:mysql://localhost:3306/todolist?useSSL=false" ;
	private String jdbcUsername="root" ; 
	private String jdbcPassword="";
	
	private static final String insertCategory="INSERT INTO categorie"+"(TitreCategorie) VALUES"+"(?);";
	private static final String insertTache="INSERT INTO tache"+"(tacheCategorie,TitreTache) VALUES"+"(?,?);";

	// select all tasks of a category	
	private static final String selectCategory="SELECT * from categorie where CategorieId =? " ; 
	private static final String selectAllCategories="SELECT * from categorie ;" ; 
	
	private static final String selectTasksWithCategory="select * from  tache where tacheCategorie =? "  ; 
	
	
	private static final String deleteTasksOfCategory="delete from  tache where tacheCategorie =? " ;
	private static final String deleteCategory="delete from  categorie where CategorieId =? " ; 
	private static final String updateCategory="update categorie set TitreCategorie=? where CategorieId =? " ; 
	// get Connection
	protected Connection getConnection(){
		Connection connection = null ; 
		try{
			Class.forName("com.mysql.cj.jdbc.Driver")  ;
			connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return connection ; 
	}
	public  void insertCategory(Categorie categorie){
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = 	connection.prepareStatement(insertCategory);
			){
			preparedStatement.setString(1, categorie.getTitreCategorie());
			preparedStatement.executeUpdate() ; 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Update task
	public  void updateCategory(Categorie categorie) throws SQLException{
		boolean rowUpdated = false ; 
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement0 = 	connection.prepareStatement(selectCategory);
			PreparedStatement preparedStatement1 = 	connection.prepareStatement(selectTasksWithCategory);
			PreparedStatement preparedStatement2 = 	connection.prepareStatement(deleteTasksOfCategory);
			PreparedStatement preparedStatement3 = 	connection.prepareStatement(updateCategory);
			PreparedStatement preparedStatement4 = 	connection.prepareStatement(insertTache);
			){
			// update logic
			// select the name of the category from before
			preparedStatement0.setInt(1, categorie.categorieId);
			ResultSet rs0 = preparedStatement0.executeQuery() ;
			String TheTitleBefore = null ; 
			while(rs0.next()){
				 TheTitleBefore  = rs0.getString("TitreCategorie") ; 
			}
			// after 
			// 1 select all the tasks with the category name
			List<String> tasksNames = new ArrayList<String>();  
			System.out.println("categorie.titreCategorie");
			System.out.println(categorie.titreCategorie);
			System.out.println("categorie.titreCategorie");
			preparedStatement1.setString(1, TheTitleBefore);
			ResultSet rs1 = preparedStatement1.executeQuery() ;
			
			System.out.println("ResultSet");
			System.out.println(rs1);
			System.out.println("ResultSet");
			while(rs1.next()){
				String TitreCategorie = rs1.getString("TitreTache");
				System.out.println("something here");
				System.out.println(TitreCategorie);
				System.out.println("something here");
				tasksNames.add(TitreCategorie)  ; 
			}
			System.out.println("the names that we got");
			System.out.println(tasksNames);
			System.out.println("the names that we got");
			//delete the tasks from
			preparedStatement2.setString(1, TheTitleBefore);
			preparedStatement2.executeUpdate() ; 
			// edit the category
			//preparedStatement.setString(1, tache.getTacheCategorie());
			preparedStatement3.setString(1, categorie.titreCategorie);
			preparedStatement3.setInt(2, categorie.categorieId);
			preparedStatement3.executeUpdate() ; 
			// add the new tasks with a loop
		    for (String name : tasksNames) {
		      // Tache tache = new Tache(categorie.titreCategorie,name);
		       preparedStatement4.setString(1, categorie.titreCategorie);
		       preparedStatement4.setString(2, name);
		       preparedStatement4.executeUpdate() ; 
		    }

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	// select Task by Id
	
	public Categorie selectCategory(int Id){
		Categorie categorie = null ;
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = 	connection.prepareStatement(selectCategory);
			){
			preparedStatement.setInt(1,Id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery() ;
			
			while(rs.next()){
				String TitreCategorie = rs.getString("TitreCategorie");
				Integer CategorieId = rs.getInt("CategorieId");
				categorie = new Categorie(TitreCategorie, CategorieId) ; 
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return categorie ; 
	}
	
	// selecting all tasks
	public List<Categorie> selectAllCategories(){
		System.out.println("you actually get in");
		System.out.println("you actually get in");
		System.out.println("you actually get in");
		System.out.println("you actually get in");
		System.out.println("you actually get in");
		List<Categorie> categorie = new ArrayList<>() ;
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = 	connection.prepareStatement(selectAllCategories);
			){
			System.out.println("theres something somewhere");
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery() ;
			System.out.println("Categories content here");
			while(rs.next()){
				String TitreCategorie = rs.getString("TitreCategorie");
				System.out.println(TitreCategorie);
				int CategorieId = rs.getInt("CategorieId");
				System.out.println(CategorieId);
				try{
					categorie.add(new Categorie(TitreCategorie, CategorieId))  ;
					
				}catch(Exception e){
					System.out.println("problem in add category ??..");
					System.out.println(e.getMessage());
				}
				
				
			}
			System.out.println("not a problem here ??..");
			for (Categorie res : categorie) {
				System.out.println(res.getTitreCategorie());
				System.out.println(res.getCategorieId());
			}
			System.out.println("not a problem here ??..");

		}catch(Exception e){
			System.out.println("are you even here");
			System.out.println("problem here");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		
		return categorie ; 
	}
	
	public List<Tache> SelectTaskOfCategory(int Id, String TitreCategorie){

		List<Tache> tache = new ArrayList<>() ;
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = 	connection.prepareStatement(selectTasksWithCategory);
			){
			System.out.println(preparedStatement);
			preparedStatement.setString(1, TitreCategorie);
			ResultSet rs = preparedStatement.executeQuery() ;
			
			while(rs.next()){
				String tacheCategorie = rs.getString("tacheCategorie");
				String TitreTache = rs.getString("TitreTache");
				System.out.println(TitreTache);
				System.out.println(tacheCategorie);
				int tacheId = rs.getInt("tacheId");
				try{
					tache.add(new Tache(tacheCategorie, TitreTache, tacheId))  ;
					
				}catch(Exception e){
					System.out.println("problem in add tache ??..");
					System.out.println(e.getMessage());
				}
				
				
			}
			System.out.println("not a problem here ??..");
			for (Tache res : tache) {
				System.out.println(res.tacheCategorie);
				System.out.println(res.tacheId);
				System.out.println(res.titreTache);
			}
			System.out.println("not a problem here ??..");

		}catch(Exception e){
			System.out.println("problem here");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		
		return tache ; 
	}
	
	// delete Task
	public  boolean deleteCategory(int Id,String titreCategorie){
		boolean rowDeleted = false ; 
		try(Connection connection = getConnection();
		    PreparedStatement preparedStatement1 = 	connection.prepareStatement(deleteTasksOfCategory);
			PreparedStatement preparedStatement2 = 	connection.prepareStatement(deleteCategory);
			){
			preparedStatement1.setString(1, titreCategorie);
			preparedStatement1.executeUpdate() ; 
			preparedStatement2.setInt(1, Id);
			rowDeleted = preparedStatement2.executeUpdate() >0 ; 
		}catch(Exception e){
			e.printStackTrace();
		}
		return rowDeleted ; 
	}
	

}
