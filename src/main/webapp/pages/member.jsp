<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Membres</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="my-4">Gestion des Membres</h1>

    <!-- Add Member Button -->
    <button type="button" class="btn btn-primary mb-4" data-toggle="modal" data-target="#addMembreModal">
        Ajouter un Membre
    </button>

    <!-- Members Table -->
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Email</th>
            <th>Rôle</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="membre" items="${membres}">
            <tr>
                <td>${membre.id}</td>
                <td>${membre.nom}</td>
                <td>${membre.prenom}</td>
                <td>${membre.email}</td>
                <td>${membre.role}</td>
                <td>
                    <a href="MembreServlet?action=edit&id=${membre.id}" class="btn btn-warning btn-sm">Modifier</a>
                    <a href="MembreServlet?action=delete&id=${membre.id}" class="btn btn-danger btn-sm">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Pagination -->
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:forEach begin="1" end="${noOfPages}" var="page">
                <li class="page-item ${page == currentPage ? 'active' : ''}">
                    <a class="page-link" href="MembreServlet?action=list&page=${page}">${page}</a>
                </li>
            </c:forEach>
        </ul>
    </nav>

    <!-- Add Member Modal -->
    <div class="modal fade" id="addMembreModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                 <form action="${pageContext.request.contextPath}/MemberServlet" method="post">
                	<div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Ajouter un Membre</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="action" value="add">
                        <div class="form-group">
                            <label for="nom">Nom</label>
                            <input type="text" class="form-control" id="nom" name="nom" required>
                        </div>
                        <div class="form-group">
                            <label for="prenom">Prénom</label>
                            <input type="text" class="form-control" id="prenom" name="prenom" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="form-group">
                            <label for="role">Rôle</label>
                            <select class="form-control" id="role" name="role" required>
                                <option value="ChefDeProjet">Chef de Projet</option>
                                <option value="Developpeur">Développeur</option>
                                <option value="Designer">Designer</option>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                        <button type="submit" class="btn btn-primary">Ajouter</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
