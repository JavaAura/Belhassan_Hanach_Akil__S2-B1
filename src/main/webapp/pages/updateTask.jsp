<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

    <!-- Edit Modal HTML -->
<div id="editTaskModal" class="modal fade" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="editTaskLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="editTaskLabel">Edit Task</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <!-- Form for editing the task -->
      <form id="editTaskForm" action="/project/tasks/update?" method="post">
        <div class="modal-body">
          <!-- Task title input -->
          <div class="mb-3">
            <label for="taskTitle" class="form-label">Title</label>
            <input type="text" class="form-control" id="taskTitle" name="titre" value="{{task.titre}}" required>
          </div>
          
          <!-- Task description input -->
          <div class="mb-3">
            <label for="taskDescription" class="form-label">Description</label>
            <textarea class="form-control" id="taskDescription" name="description" rows="3" required>{{task.description}}</textarea>
          </div>

          <!-- Task priority input -->
          <div class="mb-3">
            <label for="taskPriority" class="form-label">Priority</label>
            <select class="form-select" id="taskPriority" name="priorite" required>
              <option value="Basse" {{task.priorite == 'Basse' ? 'selected' : ''}}>Basse</option>
              <option value="Moyenne" {{task.priorite == 'Moyenne' ? 'selected' : ''}}>Moyenne</option>
              <option value="Haute" {{task.priorite == 'Haute' ? 'selected' : ''}}>Haute</option>
            </select>
          </div>

          <!-- Task status input -->
          <div class="mb-3">
            <label for="taskStatus" class="form-label">Status</label>
            <select class="form-select" id="taskStatus" name="statut" required>
              <option value="A_faire" {{task.statut == 'A_faire' ? 'selected' : ''}}>A faire</option>
              <option value="En_cours" {{task.statut == 'En_cours' ? 'selected' : ''}}>En cours</option>
              <option value="terminne" {{task.statut == 'termine' ? 'selected' : ''}}>Termine</option>
            </select>
          </div>

          <!-- Task due date input -->
          <div class="mb-3">
            <label for="taskDueDate" class="form-label">Due Date</label>
            <input type="date" class="form-control" id="taskDueDate"  ng-model="task.dateEcheance" name="dateEcheance"  required>
          </div>

         </div>
          
        <div class="modal-footer">
          <input type="hidden" name="taskId" value="{{task.id}}"/>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="submit" class="btn btn-primary">Update Task</button>
        </div>
      </form>
    </div>
  </div>
</div>
