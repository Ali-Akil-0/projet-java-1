<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>TODO List Project : Akil Ali</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
       		<link rel="stylesheet" href="css/center.css"> </link>
        </head>

        <body>

            <header class="centering">
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">

                    <ul class="navbar-nav">
                        <li><h1><a href="<%=request.getContextPath()%>/list" class="nav-link">Todo List : Akil Ali</a></h1></li>
                    </ul>
                </nav>
            </header>
            <br>
            <h3 class="text-center">Categories Form</h3>
                    <hr>
            <div class="container text-center">

                          <a href="<%=request.getContextPath()%>/list" class="btn btn-success">List Of Tasks
     </a>
     <a href="<%=request.getContextPath()%>/categories" class="btn btn-success">List Of Categories
     </a>
                    </div>
                    <br/>
                    <br/>
                    
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${category != null}">
                            <form action="updateCategory" method="post">
                        </c:if>
                        <c:if test="${category == null}">
                            <form action="insertCategory" method="post">
                        </c:if>

                       
                            <h2>
                                <c:if test="${category != null}">
                                    Edit User
                                </c:if>
                                <c:if test="${category == null}">
                                    Add New User
                                </c:if>
                            </h2>
                      

                        <c:if test="${category != null}">
                            <input type="hidden" name="id" value="<c:out value='${category.categorieId}' />" />
                        </c:if>
						
                        <fieldset class="form-group">
                            <label>Category Title</label> <input type="text" value="<c:out value='${category.titreCategorie}' />" class="form-control" name="titreCategorie" required="required">
                        </fieldset>
                      
                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>