<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>TODO List Project : Akil Ali</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"></link>
       	<link rel="stylesheet" href="css/bootstrap.css"></link>
        </head>

        <body>

            <header >
                <nav class="navbar navbar-expand-md navbar-dark text-center" style="background-color: tomato">
               

                    <ul class="navbar-nav text-center">
                    
                        <li class="text-center"><h1><a href="<%=request.getContextPath()%>/list" class="nav-link">Todo List : Akil Ali</a></h1></li>
                 
                    </ul>
                   
                </nav>
            </header>
            <br>
 
            <div class="row">

                <div class="container">
                    <h3 class="text-center">List of Tasks</h3>
                    <hr>
                    <div class="container text-center">

                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
     New Task</a>
                             <a href="<%=request.getContextPath()%>/categories" class="btn btn-success">List Of Categories
     </a>
                    </div>
                     
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                          
                                <th>Task Content</th>
                                <th>Task Category</th>
                                <th>Modify/Delete</th>
                           
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="tache" items="${listTache}">
 								<tr>
 								
 								
 							
								    <td>
                                        <c:out value="${tache.titreTache}" />
                                    </td>
                                    <td>
                                        <c:out value="${tache.tacheCategorie}" />
                                    </td>
                                    
             
                                    <td><a href="edit?id=<c:out value='${tache.tacheId}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${tache.tacheId}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>