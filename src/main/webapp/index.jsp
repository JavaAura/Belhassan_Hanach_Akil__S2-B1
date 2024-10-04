<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Task Manager</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css" />
   
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
 
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script>
	  var app = angular.module('taskApp', []);
	  app.controller('taskCtrl', function($scope)
	 {
		  
	 	$scope.getTaskDetails = function(taskId)
	 	{
	 		console.log(taskId)
		 	var employeeDetails = '';
	 		console.log("intieam")
		 	$.ajax
		 	(
			 	{
				 	url : '/project/tasks/get',
				 	type : 'post',							 	
				 	data : {"taskId" : taskId},	
				 	async : false,
				 	success : function(data, textStatus, jqXHR)
				 	{
				 		console.log("dat")
				 		taskDetails = data;
				 		
				 		 
				        
					},
					error : function(jqXHR, textStatus, error)
					{
						taskDetails = '';
						console.log('Error in getting employee details from server==>' + error);
					}							 	
				}
			);

			$scope.task = JSON.parse(taskDetails);
			
			
			return $scope.task;
		}
	 	
	 	$scope.deleteTasks = function(taskId){
	 		console.log(taskId)
	 		$.ajax(
				 	{
					 	url : '/project/tasks/delete',
					 	type : 'post',							 	
					 	data : {"taskId" : taskId},	
					 	async : false,
					 	success : function(data, textStatus, jqXHR)
					 	{
					 		
					 		
					 		 $window.location.href = '/project/tasks/list';
					 		 
					        
						},
						error : function(jqXHR, textStatus, error)
						{
							
							console.log('Error in deleting tasks from server==>' + error);
						}							 	
					})
	 	}
	 }); 
	  </script>
	  
</head>
 
</head>
<body ng-app="taskApp" ng-controller="taskCtrl">

<div class="d-flex "style="height: 100vh ">
   
    <div class="sidebar">
        <img src="${pageContext.request.contextPath}/images/logotask.png" alt="logo Page">
        <div class="icon">🚀</div>
        <div class="icon">✔️</div>
        <div class="icon">🛡️</div>
        <div class="icon">⚙️</div>
    </div>

    <div class="main-content">
        <!-- Check if the tasks attribute is available -->
       
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Task Name</th>
                        <th>Description</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Loop through each task and display the details -->
                    <c:forEach var="task" items="${tasks}">
                        <tr>
                            <td>${task.id}</td>
                            <td>${task.titre}</td>
                            <td>${task.description}</td>
                            <td>${task.statut}</td>
                            <td><a  data-bs-toggle="modal" data-bs-target="#editTaskModal"  class="edit"  ng-click="getTaskDetails('${task.id}')" >
										<i class="material-icons" 
										   data-toggle="tooltip" title="Edit">&#xE254;</i>
									</a>
									
									<a  class="delete" data-toggle="modal" ng-click="deleteTasks('${task.id}')">
										<i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
									</a>
									
									 </td>
									
									
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
   

      
         <div class="search-bar gap-1">
		    <input type="search" class="form-control rounded" placeholder="Search a project" aria-label="Search" aria-describedby="search-addon" />
		    <button type="button" class="btn btn-outline-primary" data-mdb-ripple-init>Search</button>
		</div>
        <div class="mt-5">
        <div class="d-flex justify-content-between align-items-start mb-4">
         <div class="d-flex ">
        	<h2 class="text-bold">List of projects</h2>
        </div>
        	<div>
				
				<button type="button" class="btn bg-primary bg-opacity-10 text-primary active bg-light-hover" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
    + Add Tasks
</button>
            </div>
        </div>
            <table class="table table-hover task-table">
            <thead class="table-dark">
            <tr>
                <th>#</th>
                <th>Task</th>
                <th>Status</th>
                <th>Priority</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td><div class="ml-2">
                                    <h6 class="mb-0">Kickoff meeting</h6>
                                    <div class="d-flex flex-row mt-1 text-black-50 date-time">
                                        <div><i class="fa fa-calendar-o"></i><span class="ml-2">22 May 2020 11:30 PM</span></div>
                                        <div class="ml-3"><i class="fa fa-clock-o"></i><span class="ml-2">6h</span></div>
                                    </div>
                                </div></td>
                <td><span class="badge bg-success">Completed</span></td>
                <td>High</td>
                <td><button class="btn btn-primary btn-sm">View</button></td>
            </tr>
            <tr>
                <td>2</td>
                <td>Fix bugs in login page</td>
                <td><span class="badge bg-warning">Pending</span></td>
                <td>Medium</td>
                <td><button class="btn btn-primary btn-sm">View</button></td>
            </tr>
            <tr>
                <td>3</td>
                <td>Test new API endpoints</td>
                <td><span class="badge bg-danger">In Progress</span></td>
                <td>High</td>
                <td><button class="btn btn-primary btn-sm">View</button></td>
            </tr>
            </tbody>
        </table>
        
      </div>
      
        
    
                            
    </div>
</div>
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="staticBackdropLabel">Add New Task</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form id="taskForm" action="/project/tasks/add?id=1">
        <div class="modal-body">
          <div class="mb-3">
            <label for="taskTitle" class="form-label">Title</label>
            <input type="text" class="form-control" id="taskTitle" name="titre" required>
          </div>
          <div class="mb-3">
            <label for="taskDescription" class="form-label">Description</label>
            <textarea class="form-control" id="taskDescription" name="description" rows="3" required></textarea>
          </div>
          <div class="mb-3">
            <label for="taskPriority" class="form-label">Priority</label>
            <select class="form-select" id="taskPriority" name="priorite" required>
              <option value="Basse">Basse</option>
              <option value="Moyenne">Moyenne</option>
              <option value="Haute">Haute</option>
            </select>
          </div>
          <div class="mb-3">
            <label for="taskStatus" class="form-label">Status</label>
            <select class="form-select" id="taskStatus" name="statut" required>
              <option value="A_faire">A_faire</option>
              <option value="En_cours">En_cours</option>
              <option value="terminne">terminne</option>
            </select>
          </div>
          
          <div class="mb-3">
            <label for="taskDueDate" class="form-label">Due Date</label>
            <input type="date" class="form-control" id="taskDueDate" name="dateEcheance" required>
          </div>
          <div class="mb-3">
            <label for="membreId" class="form-label">Member ID</label>
            <input type="number" class="form-control" id="membreId" name="membreId" required>
          </div>
         
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="submit" class="btn btn-primary">Save Task</button>
        </div>
      </form>
    </div>
  </div>
</div>

<jsp:include page="./pages/updateTask.jsp"></jsp:include>
 
</body>
</html>