import { AfterViewInit, Component, OnInit, ViewChild, Inject, OnDestroy  } from '@angular/core';
import {ViewTaskService } from './view-task.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Task } from '../module/task';
import { Http, Response } from '@angular/http';
import {map} from 'rxjs/operators';
import { Subject } from 'rxjs';
import { DataTableDirective } from 'angular-datatables';
import { Router } from '@angular/router';
import { AddTaskService } from '../service/add-task.service';
import { ParentTask } from '../module/parentTask';

/*class DataTablesResponse {
  data: any[];
  draw: number;
  recordsFiltered: number;
  recordsTotal: number;
}*/


@Component({
  selector: 'app-view-task',
  templateUrl: './view-task.component.html',
  styleUrls: ['./view-task.component.css']
})
export class ViewTaskComponent implements OnInit, AfterViewInit, OnDestroy {
  @ViewChild(DataTableDirective, {static: false})
  datatableElement: DataTableDirective;

  dibl:boolean;
  dtOptions: DataTables.Settings = {};
  dtOptions2: DataTables.Settings = {};
  tasks: Task[] = [];
  pttasks: ParentTask[] = [];
  ts: Task = null;
  pros = [];
  prosel = false;
  vv: number = 37;
  //public data : any;
  dtTrigger: Subject<Task> = new Subject();
  dtTrigger2: Subject<ParentTask> = new Subject();
  constructor(
    //private viewTaskService: ViewTaskService,
    private http: Http, private router: Router,
    @Inject('Task') private tskService: AddTaskService
  ) { }
    
    ngOnInit(): void {
      this.dibl = false;
      this.dtOptions = {
        pagingType: 'full_numbers',
        pageLength: 5
      };
      this.dtOptions2 = {
        pagingType: 'full_numbers',
        pageLength: 5
      };
      //this.render();
      this.tskService.fetchprojectAll()
      .subscribe(data => {
          console.log(data)
          this.pros = data;
        });
        this.http.get('http://localhost:8088/getAllTasks')
         .pipe(
            map(res => res.json())
         ).subscribe(data => {
          console.log(data)
        });
        
    }

    render(): void {
      this.datatableElement.dtInstance.
      then((dtInstance: DataTables.Api) => {
        // Destroy the table first
        dtInstance.destroy();
        $('#edbtable').empty();
        // Call the dtTrigger to rerender again
        setTimeout(() => {
          this.dtTrigger.next();
          this.dtTrigger2.next();
        });
      });
    }
  
    ngOnDestroy(): void {
      // Do not forget to unsubscribe the event
      this.dtTrigger.unsubscribe();
      this.dtTrigger2.unsubscribe();
    }

    onChange(value){
      console.log(value);
      this.prosel = true;
      var vl = +value;
      this.http.get('http://localhost:8088/getTaskByProjectId/'+vl)
         .pipe(
            map(res => res.json())
         ).subscribe(data => {
          console.log(data);
          this.tasks = data;
        });
      
        this.http.get('http://localhost:8088/getParentTaskByProjectId/'+vl)
        .pipe(
           map(res => res.json())
        ).subscribe(data => {
         console.log(data);
         this.pttasks = data;
       });
    }

    onSubmitV(tsk:number) {
    
    this.http.post('http://localhost:8088/disableTask/'+tsk, {})
    .pipe(
      map(res => res.json())
      ).subscribe(data => {
    console.log(data);
    //this.tasks = data;
    //this.dtTrigger.next();
    //this.router.navigateByUrl('/viewTask');
    window.location.reload();
  })
}
  
    private extractData(res: Response) {
      const body = res.json();
      return body || {};
    }

    ngAfterViewInit(): void {
      /*this.datatableElement.dtInstance.then((dtInstance: DataTables.Api) => {
        dtInstance.columns().every(function () {
          const that = this;
          $('input', this.footer()).on('keyup change', function () {
            alert('asdsa');
            if (that.search() !== this['value']) {
              that
                .search(this['value'])
                .draw();
            }
          });
        });
      });*/
      //this.dtTrigger.next();
    }

    editTask(tsk:number){
      alert(tsk);
      this.router.navigateByUrl('/updateTask/'+tsk);
    } 
}