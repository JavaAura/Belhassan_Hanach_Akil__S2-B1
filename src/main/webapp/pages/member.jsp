<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Members</title>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css" rel="stylesheet" />
</head>
<body>

<div class="container d-flex" style="height: 100vh;">
    <!-- Sidebar -->
    <div class="sidebar">
        <img src="../images/logotask.png" alt="Logo">
        <div class="icon">🚀</div>
        <div class="icon">✔️</div>
        <div class="icon">🛡️</div>
        <div class="icon">⚙️</div>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <!-- Search Bar -->
        <div class="search-bar gap-1">
            <input type="search" class="form-control rounded" placeholder="Search a member" aria-label="Search" aria-describedby="search-addon" />
            <button type="button" class="btn btn-outline-primary" data-mdb-ripple-init>Search</button>
        </div>

        <!-- Header and Add Member Button -->
        <div class="mt-5">
            <div class="d-flex justify-content-between align-items-start mb-4">
                <h2 class="text-bold">List of Members</h2>
                <!-- Success and Error Messages -->
                <div>
                    <% if (session.getAttribute("successMessage") != null) { %>
                        <div class="alert alert-success" role="alert">
                            <%= session.getAttribute("successMessage") %>
                        </div>
                        <% session.removeAttribute("successMessage"); %>
                    <% } %>
                    <% if (session.getAttribute("errorMessage") != null) { %>
                        <div class="alert alert-danger" role="alert">
                            <%= session.getAttribute("errorMessage") %>
                        </div>
                        <% session.removeAttribute("errorMessage"); %>
                    <% } %>
                </div>
                <!-- Add Member Button -->
                <button type="button" class="btn bg-primary bg-opacity-10 text-primary active bg-light-hover" data-bs-toggle="modal" data-bs-target="#addMembreModal" aria-pressed="true">
                    + Ajouter Member
                </button>
            </div>

            <!-- Add Member Modal -->
            <div class="modal fade" id="addMembreModal" tabindex="-1" aria-labelledby="addMembreModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="${pageContext.request.contextPath}/MemberServlet" method="post">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addMembreModalLabel">Add Member</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" name="action" value="add">
                                <div class="mb-3">
                                    <label for="nom" class="form-label">Last Name</label>
                                    <input type="text" class="form-control" id="nom" name="nom" required>
                                </div>
                                <div class="mb-3">
                                    <label for="prenom" class="form-label">First Name</label>
                                    <input type="text" class="form-control" id="prenom" name="prenom" required>
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" required>
                                </div>
                                <div class="form-group">
								    <label for="equipe">Equipe</label>
								    <select class="form-control" id="equipe" name="equipeId" required>
								        <option value="0">Tous les Equipes</option>
								        <c:forEach var="equipe" items="${equipes}">
			    <option value="${equipe.id}">${equipe.nom}</option>
			</c:forEach>
								    </select>
								</div>

                                <div class="mb-3">
                                    <label for="role" class="form-label">Role</label>
                                    <select class="form-control" id="role" name="role" required>
                                        <option value="ChefDeProjet">Project Manager</option>
                                        <option value="Developpeur">Developer</option>
                                        <option value="Designer">Designer</option>
                                    </select>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary">Add Member</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Members Table -->
            <table class="table align-middle mt-5 mb-0 bg-white">
                <thead class="bg-light">
                    <tr>
                        <th>Last Name</th>
                        <th>First Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Team</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="membre" items="${membres}">
                        <tr>
                            <td>${membre.nom}</td>
                            <td>${membre.prenom}</td>
                            <td>${membre.email}</td>
                            <td>${membre.role}</td>
                            <td>${membre.equipe.nom}</td>
                            <td class="d-flex gap-1">
                                <!-- Edit Button -->
                                <button type="button" class="btn btn-warning btn-sm btn-rounded" data-bs-toggle="modal" data-bs-target="#editModal${membre.id}">
                                    Edit
                                </button>
                                <!-- Delete Form -->
                                <a href="MemberServlet?action=delete&id=${membre.id}">
	                                <button type="submit" onclick="return confirm(${membre.id});" class="btn btn-danger btn-sm btn-rounded">
	                                    Delete
	                                </button>
	                            </a>
                            </td>
                        </tr>

                        <!-- Edit Member Modal -->
                        <div class="modal fade" id="editModal${membre.id}" tabindex="-1" aria-labelledby="editModalLabel${membre.id}" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <form action="${pageContext.request.contextPath}/MemberServlet" method="post">
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="id" value="${membre.id}">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="editModalLabel${membre.id}">Edit Member</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <label for="nom${membre.id}" class="form-label">Last Name</label>
                                                <input type="text" class="form-control" id="nom${membre.id}" name="nom" value="${membre.nom}" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="prenom${membre.id}" class="form-label">First Name</label>
                                                <input type="text" class="form-control" id="prenom${membre.id}" name="prenom" value="${membre.prenom}" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="email${membre.id}" class="form-label">Email</label>
                                                <input type="email" class="form-control" id="email${membre.id}" name="email" value="${membre.email}" required>
                                            </div>
                                            <div class="mb-3">
											    <label for="equipe" class="form-label">Equipe</label>
											    <select class="form-control" id="equipe" name="equipeId" required>
											        <c:forEach var="equipe" items="${equipes}">
											            <option value="${equipe.id}">${equipe.nom}</option>
											        </c:forEach>
											    </select>
											</div>
                                            <div class="mb-3">
                                                <label for="role${membre.id}" class="form-label">Role</label>
                                                <select class="form-control" id="role${membre.id}" name="role" required>
                                                    <option value="ChefDeProjet" ${membre.role == 'ChefDeProjet' ? 'selected' : ''}>Project Manager</option>
                                                    <option value="Developpeur" ${membre.role == 'Developpeur' ? 'selected' : ''}>Developer</option>
                                                    <option value="Designer" ${membre.role == 'Designer' ? 'selected' : ''}>Designer</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-primary">Save changes</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Pagination -->
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:forEach begin="1" end="${noOfPages}" var="page">
                        <li class="page-item ${page == currentPage ? 'active' : ''}">
                            <a class="page-link" href="MemberServlet?action=list&page=${page}">${page}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>

        </div>
    </div>
</div>

<!-- Bootstrap JS and MDB UI Kit JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"></script>

</body>
</html>
