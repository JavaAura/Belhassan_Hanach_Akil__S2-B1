<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Projects</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/index.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css"
	rel="stylesheet" />
</head>
<body>

	<div class=" d-flex " style="height: 100vh">
		<div class="sidebar">
			<img src="${pageContext.request.contextPath}/images/logotask.png"
				alt="logo Page">
			<div class="icon">🚀</div>
			<div class="icon">✔️</div>
			<div class="icon">🛡️</div>
			<div class="icon">⚙️</div>
		</div>

		<div class="main-content">
			<div class="search-bar gap-1">
				<form class="d-flex gap-1"
					action="<%=request.getContextPath()%>/projects/search"
					method="post">
					<input type="search" name="projectName" value="${searchTerm}"
						class="form-control rounded" placeholder="Search a project"
						aria-label="Search" aria-describedby="search-addon" />
					<button type="submit" class="btn btn-outline-primary"
						data-mdb-ripple-init>Search</button>
				</form>
			</div>

			<div class="mt-5">
				<div class="d-flex justify-content-between align-items-start mb-4">
					<div class="d-flex">
						<h2 class="text-bold">List of projects</h2>
					</div>
					<div>
						<%
						if (session.getAttribute("successMessage") != null) {
						%>
						<div class="alert alert-success" role="alert">
							<%=session.getAttribute("successMessage")%>
						</div>
						<%
						session.removeAttribute("successMessage");
						%>
						<%
						}
						%>

						<%
						if (session.getAttribute("errorMessage") != null) {
						%>
						<div class="alert alert-danger" role="alert">
							<%=session.getAttribute("errorMessage")%>
						</div>
						<%
						session.removeAttribute("errorMessage");
						%>
						<%
						}
						%>
					</div>
					<div>
						<button type="button"
							class="btn bg-primary bg-opacity-10 text-primary active bg-light-hover"
							data-bs-toggle="modal" data-bs-target="#exampleModal"
							aria-pressed="true">+ Add Project</button>
					</div>
				</div>

				<div class="modal fade" id="exampleModal" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">New project</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form action="<%=request.getContextPath()%>/projects/insert"
									method="post">
									<div class="mb-3">
										<label for="name" class="form-label">Project name</label> <input
											type="text" class="form-control" name="name" id="name"
											aria-describedby="nameHelp">
									</div>
									<div class="mb-3">
										<label for="description" class="form-label">Description</label>
										<textarea class="form-control" name="description"
											id="description"></textarea>
									</div>
									<div class="mb-3">
										<label for="dateDebut" class="form-label">Start date</label> <input
											type="date" name="dateDebut" class="form-control"
											id="dateDebut">
									</div>
									<div class="mb-3">
										<label for="dateFin" class="form-label">End date</label> <input
											type="date" name="dateFin" class="form-control" id="dateFin">
									</div>
									<div class="mb-3">
										<label for="etatProjet" class="form-label">Project
											status</label> <select name="etatProjet" class="form-select"
											aria-label="Default select example">
											<option selected>Choose one of the following...</option>
											<option value="en_preparation">En préparation</option>
											<option value="en_cours">En cours</option>
											<option value="en_pause">En pause</option>
											<option value="termine">Terminé</option>
											<option value="annule">Annulé</option>
										</select>
									</div>
									<div class="modal-footer">
										<button type="submit" class="btn btn-primary">Add
											project</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<table class="table align-middle mt-5 mb-0 bg-white">
					<thead class="bg-light">
						<tr>
							<th>Name</th>
							<th>Description</th>
							<th>Status</th>
							<th>Start date</th>
							<th>End date</th>
							<th>N° Members</th>
							<th>N° Tasks</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="project" items="${listProjects}"
							begin="${(currentPage-1)*recordsPerPage}"
							end="${((currentPage-1)*recordsPerPage)+recordsPerPage - 1 }">
							<tr>
								<td>
									<div class="d-flex align-items-center">
										<div class="ms-3">
											<p class="fw-bold mb-1">${project.nom}</p>
										</div>
									</div>
								</td>
								<td>
									<p class="fw-normal mb-1">${project.description}</p>
								</td>
								<td><span
									class="badge bg-success bg-opacity-10 text-success">${project.etatProjet}</span>
								</td>
								<td>${project.dateDebut}</td>
								<td>${project.dateFin}</td>
								<td><span
									class="badge bg-warning bg-opacity-10 text-success">${project.taskCount}</span>
								</td>
								<td><span
									class="badge bg-primary bg-opacity-10 text-success">${project.membreCount}</span>
								</td>
								<td class="d-flex gap-1">

									<button type="button"
										class="btn btn-warning btn-sm btn-rounded"
										data-bs-toggle="modal"
										data-bs-target="#editModal${project.id}">Edit</button>

									<form action="<%=request.getContextPath()%>/projects/delete"
										method="post">
										<input type="hidden" name="id" value="${project.id}">
										<button type="submit"
											onclick="return confirm('Are you sure you want to delete this project?');"
											class="btn btn-danger btn-sm btn-rounded">Delete</button>
									</form>
									<form action="" method="post">
										<input type="hidden" name="id" value="${project.id}">
										<button type="button"
											class="btn btn-success btn-sm btn-rounded">access</button>
									</form>

								</td>
							</tr>
							<div class="modal fade" id="editModal${project.id}" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title">Edit project</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											<form
												action="<%=request.getContextPath()%>/projects/update"
												method="post">
												<input type="hidden" name="id" value="${project.id}">
												<div class="mb-3">
													<label for="name${project.id}" class="form-label">Project
														name</label> <input type="text" class="form-control" name="name"
														id="name${project.id}" value="${project.nom}">
												</div>
												<div class="mb-3">
													<label for="description${project.id}" class="form-label">Description</label>
													<textarea class="form-control" name="description"
														id="description${project.id}">${project.description}</textarea>
												</div>
												<div class="mb-3">
													<label for="dateDebut${project.id}" class="form-label">Start
														date</label> <input type="date" name="dateDebut"
														class="form-control" id="dateDebut${project.id}"
														value="${project.dateDebut}">
												</div>
												<div class="mb-3">
													<label for="dateFin${project.id}" class="form-label">End
														date</label> <input type="date" name="dateFin"
														class="form-control" id="dateFin${project.id}"
														value="${project.dateFin}">
												</div>
												<div class="mb-3">
													<label for="etatProjet${project.id}" class="form-label">Project
														status</label> <select name="etatProjet" class="form-select"
														id="etatProjet${project.id}">
														<option value="en_preparation"
															${project.etatProjet == 'en_preparation' ? 'selected' : ''}>En
															préparation</option>
														<option value="en_cours"
															${project.etatProjet == 'en_cours' ? 'selected' : ''}>En
															cours</option>
														<option value="en_pause"
															${project.etatProjet == 'en_pause' ? 'selected' : ''}>En
															pause</option>
														<option value="termine"
															${project.etatProjet == 'termine' ? 'selected' : ''}>Terminé</option>
														<option value="annule"
															${project.etatProjet == 'annule' ? 'selected' : ''}>Annulé</option>
													</select>
												</div>
												<div class="modal-footer">
													<button type="submit" class="btn btn-primary">Save
														changes</button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</tbody>
				</table>
				<div class="container mt-4">
				    <!-- Pagination -->
				    <nav aria-label="Page navigation">
				        <ul class="pagination justify-content-center">
				            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
				                <a class="page-link" href="?page=${currentPage - 1}" aria-label="Previous">
				                    <span aria-hidden="true">&laquo;</span>
				                </a>
				            </li>
				            
				            <c:forEach begin="1" end="${totalPages}" var="i">
				                <li class="page-item ${currentPage == i ? 'active' : ''}">
				                    <a class="page-link" href="?page=${i}">${i}</a>
				                </li>
				            </c:forEach>
				
				            <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
				                <a class="page-link" href="?page=${currentPage + 1}" aria-label="Next">
				                    <span aria-hidden="true">&raquo;</span>
				                </a>
				            </li>
				        </ul>
				    </nav>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"></script>

</body>
</html>