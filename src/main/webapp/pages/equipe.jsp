<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teams</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css" rel="stylesheet" />
</head>
<body>

<div class="container d-flex" style="height: 100vh">
    <div class="sidebar">
        <img src="../images/logotask.png" alt="logo Page">
        <div class="icon">🚀</div>
        <div class="icon">✔️</div>
        <div class="icon">🛡️</div>
        <div class="icon">⚙️</div>
    </div>

    <div class="main-content">
        <div class="search-bar gap-1">
            <input type="search" class="form-control rounded" placeholder="Search a team" aria-label="Search" aria-describedby="search-addon" />
            <button type="button" class="btn btn-outline-primary" data-mdb-ripple-init>Search</button>
        </div>

        <div class="mt-5">
            <div class="d-flex justify-content-between align-items-start mb-4">
                <div class="d-flex">
                    <h2 class="text-bold">List of Teams</h2>
                </div>
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
                <div>
                    <button type="button" class="btn bg-primary bg-opacity-10 text-primary active bg-light-hover" data-bs-toggle="modal" data-bs-target="#addModal">
                        + Add Team
                    </button>
                </div>
            </div>

            <!-- Add Team Modal -->
            <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addModalLabel">New Team</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="${pageContext.request.contextPath}/EquipeServlet?action=add" method="post">
                                <div class="mb-3">
                                    <label for="nom" class="form-label">Team Name</label>
                                    <input type="text" class="form-control" name="nom" id="nom" required>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary">Add Team</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Teams Table -->
            <table class="table align-middle mt-5 mb-0 bg-white">
                <thead class="bg-light">
                    <tr>
                        
                        <th>Name</th>
                        <th>Member Count</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="equipe" items="${equipes}">
                    <tr>
                        
                        <td>${equipe.nom}</td>
                        <td>${equipe.membres != null ? equipe.membres.size() : 0}</td>
                        <td class="d-flex gap-1">
                            <button type="button" class="btn btn-info btn-sm btn-rounded" data-bs-toggle="collapse" data-bs-target="#members${equipe.id}" aria-expanded="false" aria-controls="members${equipe.id}">
                                Show Members
                            </button>
                            <button type="button" class="btn btn-warning btn-sm btn-rounded" data-bs-toggle="modal" data-bs-target="#editModal${equipe.id}">
                                Edit
                            </button>
                            <a href="EquipeServlet?action=delete&id=${equipe.id}">
                                <button type="submit" onclick="return confirm('Are you sure you want to delete this team?');" class="btn btn-danger btn-sm btn-rounded">
                                    Delete
                                </button>
                            </a>
                        </td>
                    </tr>

                    <!-- Members List (Collapsible) -->
                    <tr class="collapse" id="members${equipe.id}">
					    <td colspan="4">
					        <ul class="list-group">
					            <c:choose>
					                <c:when test="${not empty equipe.membres}">
					                    <c:forEach var="member" items="${equipe.membres}">
					                        <li class="list-group-item">${member.nom}</li> <!-- Adjust this based on your Member object -->
					                    </c:forEach>
					                </c:when>
					                <c:otherwise>
					                    <li class="list-group-item">No members in this team.</li>
					                </c:otherwise>
					            </c:choose>
					        </ul>
					    </td>
					</tr>

                    <!-- Edit Modal -->
                    <div class="modal fade" id="editModal${equipe.id}" tabindex="-1" aria-labelledby="editModalLabel${equipe.id}" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Edit Team</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form action="${pageContext.request.contextPath}/EquipeServlet?action=update" method="post">
                                        <input type="hidden" name="id" value="${equipe.id}">
                                        <div class="mb-3">
                                            <label for="nom${equipe.id}" class="form-label">Team Name</label>
                                            <input type="text" class="form-control" name="nom" id="nom${equipe.id}" value="${equipe.nom}" required>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-primary">Save changes</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"></script>

</body>
</html>
