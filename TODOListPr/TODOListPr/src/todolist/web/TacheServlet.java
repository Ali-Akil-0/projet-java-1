package todolist.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todolist.dao.CategoryDAO;
import todolist.dao.TacheDAO;
import todolist.model.Categorie;
import todolist.model.Tache;

/**
 * Servlet implementation class TacheServlet
 */
@WebServlet("/")
public class TacheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TacheDAO tacheDAO ; 
	private CategoryDAO categoryDAO ; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TacheServlet() {
      this.tacheDAO = new TacheDAO() ; 
      this.categoryDAO  = new CategoryDAO() ; 
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	this.doGet(request, response);
    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String action = request.getServletPath() ; 
	  switch (action) {
	case "/new":
		showNewForm(request,response);
		break;
	case "/ViewTasks":
		try {
			ViewTasks(request,response);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		break;
	case "/newCategory":
		showNewFormCategory(request,response);
		break;
	case "/insert":
		try{
			insertTache(request,response ) ; 
		}catch(Exception e){
			e.printStackTrace();
		}
		break;
	case "/insertCategory":
		try{
			insertCategory(request,response ) ; 
		}catch(Exception e){
			e.printStackTrace();
		}
		break;
	case "/categories":
		try{
			listCategories(request,response ) ; 
		}catch(Exception e){
			e.printStackTrace();
		}
		break;
	case "/delete":
		try{
			deleteTache(request,response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		break;
	case "/deleteCategory":
		try{
			deleteCategory(request,response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		break;
	case "/edit":
		try{
			ShowEditForm(request,response);

		}catch(Exception e){
			e.printStackTrace();
		}
		break;
	case "/editCategory":
		try{
			ShowEditFormCategory(request,response);

		}catch(Exception e){
			e.printStackTrace();
		}
		break;
	case "/update":
		try{
			updateTask(request,response);

		}catch(Exception e){
			e.printStackTrace();
		}
		break;
	case "/updateCategory":
		try{
			updateCategory(request,response);

		}catch(Exception e){
			e.printStackTrace();
		}
		break;
	default:
		try{
			listTasks(request,response);

		}catch(Exception e){
			System.out.println("this exception");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		break;
	}
	} 
	
	private void showNewForm(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		System.out.println("categoriesssssssssssssssssssssssssssssssssss");
		 List<Categorie> categories = categoryDAO.selectAllCategories() ; 
		 request.setAttribute("categories", categories);
		 System.out.println("categoriesssssssssssssssssssssssssssssssss");
		 System.out.println(categories);
		 System.out.println("categoriesssssssssssssssssssssssssss");
		RequestDispatcher dispatcher = request.getRequestDispatcher("tache-form.jsp") ; 
		dispatcher.forward(request, response);
	}
	private void showNewFormCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("category-form.jsp") ; 
		dispatcher.forward(request, response);
	}
	
	private void insertTache(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		 String tacheCategorie = request.getParameter("tacheCategorie")  ; 
		 String TitreTache = request.getParameter("titreTache")  ; 
		 Tache tache = new Tache(tacheCategorie, TitreTache ) ;
		 tacheDAO.insertTask(tache);
		 response.sendRedirect("list");
	}
	private void insertCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		 String titreCategorie = request.getParameter("titreCategorie")  ; 
		 Categorie categorie = new Categorie(titreCategorie ) ;
		 categoryDAO.insertCategory(categorie);
		 response.sendRedirect("categories");
	}
	
	private void deleteTache(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		Integer Id = Integer.parseInt(request.getParameter("id") ) ; 
		 tacheDAO.deleteTask(Id);
		 response.sendRedirect("list");
	}
	
	private void deleteCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		Integer Id = Integer.parseInt(request.getParameter("id") ) ; 
		String titreCategorie = request.getParameter("titreCategorie"); 
		 categoryDAO.deleteCategory(Id,titreCategorie);
		 response.sendRedirect("categories");
	}
	
	private void ShowEditForm(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		System.out.println("categories");
		Integer Id = Integer.parseInt(request.getParameter("id") ) ; 
		 Tache existingtask = tacheDAO.selectTask(Id);
		 System.out.println("categoriesssssssssssssssssssssssssssssssssss");
		 List<Categorie> categories = categoryDAO.selectAllCategories() ; 
		 request.setAttribute("categories", categories);
		 System.out.println("categoriesssssssssssssssssssssssssssssssss");
		 System.out.println(categories);
		 System.out.println("categoriesssssssssssssssssssssssssss");
		 System.out.println("existingtask");
		 System.out.println(existingtask);
		 System.out.println("existingtask");
		 RequestDispatcher dispatcher = request.getRequestDispatcher("tache-form.jsp");
		 request.setAttribute("tache", existingtask);
		 dispatcher.forward(request, response);

	}
	private void ShowEditFormCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		Integer Id = Integer.parseInt(request.getParameter("id") ) ; 
		 Categorie existingCategory = categoryDAO.selectCategory(Id);
		 System.out.println("existingCategory");
		 System.out.println(existingCategory);
		 System.out.println("existingCategory");
		 RequestDispatcher dispatcher = request.getRequestDispatcher("category-form.jsp");
		 request.setAttribute("category", existingCategory);
		 dispatcher.forward(request, response);

	}
	
	private void updateTask(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		 Integer Id = Integer.parseInt(request.getParameter("id") ) ; 
		 String tacheCategorie = request.getParameter("tacheCategorie")  ; 
		 String TitreTache = request.getParameter("titreTache")  ; 
		 Tache tache = new Tache(tacheCategorie, TitreTache,Id) ; 
		try {
			tacheDAO.updateTask(tache);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("list");
		 
	}
	private void updateCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		 Integer Id = Integer.parseInt(request.getParameter("id") ) ; 
		 String tacheCategorie = request.getParameter("titreCategorie")  ; 
		 Categorie categorie = new Categorie(tacheCategorie,Id) ; 
		try {
			categoryDAO.updateCategory(categorie);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("categories");
		 
	}

	private void listTasks(HttpServletRequest request,HttpServletResponse response) throws SQLException,ServletException,IOException{
		try{
			List<Tache> listTache = tacheDAO.selectAllTasks();
			System.out.println("liste des taches");
			System.out.println(listTache);
			request.setAttribute("listTache", listTache);
			System.out.println("what I finally have ??..");
			for (Tache res : listTache) {
				System.out.println(res.titreTache);
				System.out.println(res.tacheCategorie);
				System.out.println(res.tacheId);
			
			}
			System.out.println("what I finally have ??..");


		}catch(Exception e){
			System.out.println("problem hope");
			System.out.println(e.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("tache-list.jsp");
		dispatcher.forward(request, response);
		
	}
	private void listCategories(HttpServletRequest request,HttpServletResponse response) throws SQLException,ServletException,IOException{
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaamp IIIIII in list categories ????????????");		
		// also show the categories
			System.out.println("liste des categories");
			List<Categorie> listeCategories = categoryDAO.selectAllCategories() ; 
			System.out.println("liste des categories");
			System.out.println(listeCategories);
			request.setAttribute("listeCategories", listeCategories);
			System.out.println("what is this ??..");
	try{
			for (Categorie res : listeCategories) {
				System.out.println(res.categorieId);
				System.out.println(res.titreCategorie);			
			}
			System.out.println("what is this ??..");


		}catch(Exception e){
			System.out.println("problem hope");
			System.out.println(e.getMessage());
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Categorie-list.jsp");
		dispatcher.forward(request, response);
		
	}
	private void ViewTasks(HttpServletRequest request,HttpServletResponse response) throws SQLException,ServletException,IOException{
		try{
			// select tasks of a specific Categorie
			// send the name also
			Integer CategoryId = Integer.valueOf(request.getParameter("id"))  ; 
			String titreCategorie = request.getParameter("titreCategorie")  ; 
			List<Tache> listTache = categoryDAO.SelectTaskOfCategory(CategoryId,titreCategorie);
			System.out.println("liste des taches");
			System.out.println(listTache);
			request.setAttribute("listTache", listTache);
			System.out.println("what I finally have ??..");
			for (Tache res : listTache) {
				System.out.println(res.titreTache);
				System.out.println(res.tacheCategorie);
				System.out.println(res.tacheId);
			
			}
			System.out.println("what I finally have ??..");


		}catch(Exception e){
			System.out.println("problem hope");
			System.out.println(e.getMessage());
		}
		request.setAttribute("titreCategorie1", request.getParameter("titreCategorie") );
		RequestDispatcher dispatcher = request.getRequestDispatcher("categorie-tasks.jsp");
		dispatcher.forward(request, response);
		
	}

	
}
