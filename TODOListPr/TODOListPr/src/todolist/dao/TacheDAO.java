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

public class TacheDAO {
	private String jdbcURL="jdbc:mysql://localhost:3306/todolist?useSSL=false" ;
	private String jdbcUsername="root" ; 
	private String jdbcPassword="";
	
	
	private static final String insertTache="INSERT INTO tache"+"(tacheCategorie,TitreTache) VALUES"+"(?,?);";
	
	// get all categories
	private static final String selectAllCategories="SELECT * from categorie ;" ; 

	// end all
	private static final String selectTache="SELECT * from tache where tacheId=? " ; 
	private static final String selectAllTasks="SELECT * from tache ;" ; 
	private static final String deleteTache="delete from  tache where tacheId=? " ; 
	private static final String updateTache="update tache set TitreTache=? where tacheId=? " ; 
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
	
	// Creating task
	public  void insertTask(Tache tache){
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = 	connection.prepareStatement(insertTache);
			){
			preparedStatement.setString(1, tache.getTacheCategorie());
			preparedStatement.setString(2, tache.getTitreTache());
			preparedStatement.executeUpdate() ; 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Update task
	public  boolean updateTask(Tache tache) throws SQLException{
		boolean rowUpdated = false ; 
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = 	connection.prepareStatement(updateTache);
			){
			//preparedStatement.setString(1, tache.getTacheCategorie());
			preparedStatement.setString(1, tache.getTitreTache());
			preparedStatement.setInt(2, tache.getTacheId());
			rowUpdated = preparedStatement.executeUpdate() >0 ; 
		}catch(Exception e){
			e.printStackTrace();
		}
		return rowUpdated ; 
	}
	// select Task by Id
	
	public Tache selectTask(int Id){
		Tache tache = null ;
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = 	connection.prepareStatement(selectTache);
			){
			// select all here
			preparedStatement.setInt(1,Id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery() ;
			
			while(rs.next()){
				String tacheCategorie = rs.getString("tacheCategorie");
				String TitreTache = rs.getString("TitreTache");
				tache = new Tache(tacheCategorie, TitreTache, Id) ; 
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return tache ; 
	}
	// selecting all tasks
	public List<Tache> selectAllTasks(){
		List<Tache> tache = new ArrayList<>() ;
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = 	connection.prepareStatement(selectAllTasks);
			){
			System.out.println(preparedStatement);
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
	public  boolean deleteTask(int Id){
		boolean rowDeleted = false ; 
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = 	connection.prepareStatement(deleteTache);
			){
			preparedStatement.setInt(1, Id);
			rowDeleted = preparedStatement.executeUpdate() >0 ; 
		}catch(Exception e){
			e.printStackTrace();
		}
		return rowDeleted ; 
	}
	
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
	
	
	
	
	
}
