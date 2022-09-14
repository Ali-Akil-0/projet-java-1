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
             <h3 class="text-center">Tasks Form</h3>
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
                        <c:if test="${tache != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${tache == null}">
                            <form action="insert" method="post">
                        </c:if>

                       
                            <h2>
                                <c:if test="${tache != null}">
                                    Edit User
                                </c:if>
                                <c:if test="${tache == null}">
                                    Add New User
                                </c:if>
                            </h2>
                      

                        <c:if test="${tache != null}">
                            <input type="hidden" name="id" value="<c:out value='${tache.tacheId}' />" />
                        </c:if>
						
                        <fieldset class="form-group">
                            <label>Task Title</label> <input type="text" value="<c:out value='${tache.titreTache}' />" class="form-control" name="titreTache" required="required">
                        </fieldset>
                      
                        <fieldset class="form-group" >
                            <label>Task Category</label> 
                                 
							 <c:if test="${tache != null}">
                             <label>Category</label> <input type="text" value="<c:out value='${tache.tacheCategorie}' />" class="form-control" name="tacheCategorie" required="required" disabled="disabled">

                                </c:if>
                                <c:if test="${tache == null}">
                                    <select name="tacheCategorie" required="required" class="custom-select">
							  <c:forEach items="${categories}" var="category" varStatus="loop">
							    <option value="${category.titreCategorie}">
							        ${category.titreCategorie}
							    </option>
							  </c:forEach>
							</select>
                                </c:if>                       
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>
 <style>
        .custom-select {
  position: relative;
  font-family: Arial;
}

.custom-select select {
  display: none; /*hide original SELECT element: */
}

.select-selected {
  background-color: DodgerBlue;
}

/* Style the arrow inside the select element: */
.select-selected:after {
  position: absolute;
  content: "";
  top: 14px;
  right: 10px;
  width: 0;
  height: 0;
  border: 6px solid transparent;
  border-color: #fff transparent transparent transparent;
}

/* Point the arrow upwards when the select box is open (active): */
.select-selected.select-arrow-active:after {
  border-color: transparent transparent #fff transparent;
  top: 7px;
}

/* style the items (options), including the selected item: */
.select-items div,.select-selected {
  color: #ffffff;
  padding: 8px 16px;
  border: 1px solid transparent;
  border-color: transparent transparent rgba(0, 0, 0, 0.1) transparent;
  cursor: pointer;
}

/* Style items (options): */
.select-items {
  position: absolute;
  background-color: DodgerBlue;
  top: 100%;
  left: 0;
  right: 0;
  z-index: 99;
}

/* Hide the items when the select box is closed: */
.select-hide {
  display: none;
}

.select-items div:hover, .same-as-selected {
  background-color: rgba(0, 0, 0, 0.1);
}
        </style>

        </html>