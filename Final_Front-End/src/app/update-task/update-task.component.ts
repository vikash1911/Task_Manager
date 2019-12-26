import { Component, OnInit, Inject } from '@angular/core';
import {Router} from '@angular/router';
import { from } from 'rxjs';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { Options } from 'ng5-slider';
import { Task } from '../module/task';
import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { catchError } from 'rxjs/operators';
import { AddTaskService } from '../service/add-task.service';
import { ActivatedRoute } from "@angular/router";
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-update-task',
  templateUrl: './update-task.component.html',
  styleUrls: ['./update-task.component.css']
})
export class UpdateTaskComponent implements OnInit {

  task11: string;
  priority:number;
  parentIdVal:number;
  parentTask: string;
  start_Date: Date;
  end_Date: Date;
  showmsg: boolean;

  tasks: Array<String> = [];
  taskValus: Array<String> = [];
  ptid: number = 1;
  message: string = '';
  pros = [];
  pts = [];
  users = [];
  ptask = false;
  submitted = false;
  showmsgVal: boolean;
  isValidDate = true;
  error:any={isError:false,errorMessage:''};

  constructor(private fb:FormBuilder, 
    @Inject('Task') private tskService: AddTaskService,
    private route: ActivatedRoute, public datepipe: DatePipe) { }
  updateTaskForm: FormGroup;

  ngOnInit() {
    this.showmsg = false;
    this.updateTaskForm = this.fb.group({
      task:['', Validators.required],
      parentTask:['', Validators.required],
      priority:[''],
      start_Date: ['', Validators.required],
      end_Date: ['', Validators.required],
      end_Task: [''],
      project_ID: [''],
      user_ID: ['', Validators.required]
    });
    this.tskService.fetchpt()
    .subscribe(data => {
      console.log(data)
      //this.tasks = data;
      this.pts = data;
    })

    this.tskService.fetchprojectAll()
  .subscribe(data => {
      console.log(data)
      this.pros = data;
    });

    this.tskService.fetchUserAll()
  .subscribe(data => {
      console.log(data)
      this.users = data;
    });

    this.task11 = this.route.snapshot.paramMap.get("id");

    this.tskService.fetchAllTask()
    .subscribe(data => {
      console.log(data)
      this.taskValus = data;
      //alert("sd::"+this.taskValus.length);
      for(let gg1 of this.taskValus){
        //alert('zxsdfds');
        if(this.task11 === gg1['task_ID'].toString()){
          //alert('zxsdfds');
          console.log(gg1);
        for(var i in gg1){
            if(i === 'project_ID'){
              this.updateTaskForm.controls['project_ID'].setValue(gg1[i]);
            }
            if(i === 'task'){
              this.updateTaskForm.controls['task'].setValue(gg1[i]);
            }
            if(i === 'priority'){
              this.updateTaskForm.controls['priority'].setValue(gg1[i]);
            }
            if(i === 'parentTask_id'){
              this.updateTaskForm.controls['parentTask'].setValue(gg1[i]);
            }
            if(i === 'start_Date'){
              let ss = this.datepipe.transform(gg1[i], 'yyyy-MM-dd');
              this.updateTaskForm.controls['start_Date'].setValue(ss);
            }
            if(i === 'end_Date'){
              let ss1 = this.datepipe.transform(gg1[i], 'yyyy-MM-dd');
              this.updateTaskForm.controls['end_Date'].setValue(ss1);
            }
            if(i === 'user_ID'){
              this.updateTaskForm.controls['user_ID'].setValue(gg1[i]);
            }
        }
        }
      } 
    })
    
    this.updateTaskForm.get('project_ID').disable();
    
  }

  get f() { return this.updateTaskForm.controls; }

  onSubmit() {
    //alert('sadsa');
    this.error={isError:false,errorMessage:''};
    var stdt = this.updateTaskForm.get('start_Date').value;
    var eddt = this.updateTaskForm.get('end_Date').value;
    var sdw = this.validateDates(stdt, eddt);
    if(sdw){
      this.task11 = this.route.snapshot.paramMap.get("id");
      this.updateTask(this.task11);
      this.ptask = true;
      this.submitted = true;
    }
   }

   updateTask(task11: string){
  
    /*for(let gg of this.tasks){
      for(var i in gg){
        if(i === 'parent_Task' && this.updateTaskForm.value.parentTask === gg[i]){
          this.ptid=gg['parent_ID'];
        }
      }
    }*/
    //alert(this.ptid);
    var pttid = this.updateTaskForm.get('parentTask').value;
    this.tskService.updatetsk(this.updateTaskForm.value, +task11, pttid)
    .then(data => {
      this.message = data.message
      console.log(data)
      // this.courses = data
      this.showmsgVal = true;
    })
  }

  validateDates(sDate: string, eDate: string){
    this.isValidDate = true;
    if((sDate == null || eDate ==null)){
      this.error={isError:true,errorMessage:'Start date and end date are required.'};
      this.isValidDate = false;
    }

    if((sDate != null && eDate !=null) && (eDate) < (sDate)){
      this.error={isError:true,errorMessage:'End date should be grater then start date.'};
      this.isValidDate = false;
    }
    return this.isValidDate;
  }

  reset(){
    //alert("reset");
  }

  value: number = 0;
  options: Options = {
    floor: 0,
    ceil: 30
  };

}
