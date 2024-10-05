<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Projects</title>
    <link rel="stylesheet" type="text/css" href="../css/index.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>

<div  class="d-flex "style="height: 100vh">
    <div class="sidebar">
        <img src="../images/logotask.png" alt="logo Page">
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
				<button type="button" class="btn bg-primary bg-opacity-10 text-primary active bg-light-hover" data-mdb-ripple-init role="button" aria-pressed="true">
				    + Add Project
				</button>
            </div>
        </div>
        
            <table class="table align-middle mt-5 mb-0 bg-white">
                <thead class="bg-light">
                    <tr>
                        <th>Name</th>
                        <th>Title</th>
                        <th>Status</th>
                        <th>Start date</th>
                        <th>End date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <div class="d-flex align-items-center">
                                <div class="ms-3">
                                    <p class="fw-bold mb-1">John Doe</p>
                                </div>
                            </div>
                        </td>
                        <td>
                            <p class="fw-normal mb-1">Software engineer</p>
                        </td>
                        <td>
							<span class="badge bg-success bg-opacity-10 text-success">Active</span>
                        </td>
                        <td>10-02-2002</td>
                       	<td>26-06-2002</td>
                        <td>
                            <button type="button" class="btn btn-warning btn-sm btn-rounded">Edit</button>
							<button type="button" class="btn btn-danger btn-sm btn-rounded">Delete</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
