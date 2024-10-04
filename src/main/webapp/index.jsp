<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/index.css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="font-Awesome">
</head>
 
</head>
<body>

<div class="d-flex "style="height: 100vh ">
   
    <div class="sidebar">
        <img src="./images/logotask.png" alt="logo Page">
        <div class="icon">🚀</div>
        <div class="icon">✔️</div>
        <div class="icon">🛡️</div>
        <div class="icon">⚙️</div>
    </div>

    <div class="main-content">
       
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
    + Add TAsks
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
       ppppp
         <c:forEach var="task" items="${tasks}" begin="0">
							<div>
								
								<div>${task.id}</div>
								
							</div>
		</c:forEach>
    
                            
    </div>
</div>
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="staticBackdropLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">save</button>
      </div>
    </div>
  </div>
</div>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
 
</body>
</html>