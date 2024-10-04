<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Projects</title>
    <link rel="stylesheet" type="text/css" href="../css/index.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css" rel="stylesheet" />
</head>
<body>

<div class="container d-flex">
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
                <div class="d-flex">
                    <h2 class="text-bold">List of projects</h2>
                </div>
                <div>
                    <button type="button" class="btn bg-primary bg-opacity-10 text-primary active bg-light-hover" data-bs-toggle="modal" data-bs-target="#exampleModal" aria-pressed="true">
                        + Add Project
                    </button>
                </div>
            </div>

            <!-- Modal for adding a new project -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">New project</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="projects" method="post">
                                <input type="hidden" name="action" value="insert">
                                <div class="mb-3">
                                    <label for="name" class="form-label">Project name</label>
                                    <input type="text" class="form-control" name="name" id="name" aria-describedby="nameHelp">
                                </div>
                                <div class="mb-3">
                                    <label for="description" class="form-label">Description</label>
                                    <textarea class="form-control" name="description" id="description"></textarea>
                                </div>
                                <div class="mb-3">
                                    <label for="dateDebut" class="form-label">Start date</label>
                                    <input type="date" name="dateDebut" class="form-control" id="dateDebut">
                                </div>
                                <div class="mb-3">
                                    <label for="dateFin" class="form-label">End date</label>
                                    <input type="date" name="dateFin" class="form-control" id="dateFin">
                                </div>
                                <div class="mb-3">
                                    <label for="etatProjet" class="form-label">Project status</label>
                                    <select name="etatProjet" class="form-select" aria-label="Default select example">
                                        <option selected>Choose one of the following...</option>
                                        <option value="en_preparation">En préparation</option>
                                        <option value="en_cours">En cours</option>
                                        <option value="en_pause">En pause</option>
                                        <option value="termine">Terminé</option>
                                        <option value="annule">Annulé</option>
                                    </select>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary">Add project</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Table to display projects -->
            <table class="table align-middle mt-5 mb-0 bg-white">
                <thead class="bg-light">
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Start date</th>
                        <th>End date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="project" items="${listProjects}">
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
                        <td>
                            <span class="badge bg-success bg-opacity-10 text-success">${project.etatProjet}</span>
                        </td>
                        <td>${project.dateDebut}</td>
                        <td>${project.dateFin}</td>
                        <td>
                            <button type="button" class="btn btn-warning btn-sm btn-rounded">Edit</button>
                            <button type="button" class="btn btn-danger btn-sm btn-rounded">Delete</button>
                        </td>
                    </tr>
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
