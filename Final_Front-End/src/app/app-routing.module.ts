import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ViewTaskComponent } from './view-task/view-task.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { UpdateTaskComponent } from './update-task/update-task.component';
import { AddUserComponent } from './add-user/add-user.component';
import { AddProjectComponent } from './add-project/add-project.component';



const routes: Routes = [
  {path: 'viewTask', component: ViewTaskComponent },
  {path: 'addTask', component: AddTaskComponent },
  {path: 'updateTask/:id', component: UpdateTaskComponent },
  {path: 'addUser', component: AddUserComponent },
  {path: 'addProject', component: AddProjectComponent },
  {path: '', component: ViewTaskComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
