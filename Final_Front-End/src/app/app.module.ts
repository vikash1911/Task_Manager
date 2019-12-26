import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatSliderModule} from '@angular/material/slider';
import { Ng5SliderModule } from 'ng5-slider';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { ViewTaskComponent } from './view-task/view-task.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { UpdateTaskComponent } from './update-task/update-task.component';
import { HttpClientModule } from '@angular/common/http';
import {HttpModule, Http} from '@angular/http'
import { AddTaskService } from './service/add-task.service';
import { AddUserService } from './service/add-user.service';
import { AddProjectService } from './service/add-project.service';
import {DataTableModule} from "angular-6-datatable";
import { DataTablesModule } from 'angular-datatables';
import { DatePipe } from '@angular/common';
import { AddProjectComponent } from './add-project/add-project.component';
import { AddUserComponent } from './add-user/add-user.component';

@NgModule({
  exports: [
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule
  ],
  declarations: [
    AppComponent,
    ViewTaskComponent,
    AddTaskComponent,
    UpdateTaskComponent,
    AddProjectComponent,
    AddUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    DataTableModule,
    FormsModule,
    ReactiveFormsModule,
    Ng5SliderModule,
    HttpClientModule,
    HttpModule,
    DataTablesModule
  ],
  providers: [DatePipe, { provide: 'Task',   useClass:    AddTaskService },
  { provide: 'User',   useClass:    AddUserService },
  { provide: 'Project',   useClass:    AddProjectService }],
  bootstrap: [AppComponent]
})
export class AppModule { }
