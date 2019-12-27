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
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {
  task: string;
  priority:number;
  parentIdVal:number;
  parentTask: string;
  start_Date: Date;
  end_Date: Date;
  showmsgVal: boolean;

  tasks: Array<String> = [];
  ptt: Array<String> = [];
  editTask: Task;
  tasksVal: Observable<Task[]>;
  pros = [];
  pts = [];
  users = [];
  message: string = ''
  ptid: number = 1;
  marked = false;
  theCheckbox = false;
  submitted = false;
  ptask = false;
  isValidDate = true;
  error:any={isError:false,errorMessage:''};

  constructor(private fb:FormBuilder, 
    @Inject('Task') private tskService: AddTaskService
    , public datepipe: DatePipe) { }
  addTaskForm: FormGroup; 

  ngOnInit(){
    this.addTaskForm = this.fb.group({
      task:['', Validators.required],
      priority:[''],
      theCheckbox:[''],
      parentTask:['', Validators.required],
      start_Date: ['', Validators.required],
      end_Date: ['', Validators.required],
      end_Task: [''],
      project_ID: ['', Validators.required],
      user_ID: ['', Validators.required]
    })

    console.log('Do any initialization here...')
    this.showmsgVal = false;
    this.setdates();
    // this.http.get('http://localhost:4200/assets/courses.json')
    this.tskService.fetchpt()
  .subscribe(data => {
      console.log(data)
      this.tasks = data;
      this.pts = data;
      this.setdates();
    });

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
    
  }

  get f() { return this.addTaskForm.controls; }

  toggleVisibility(e){
    this.marked= e.target.checked;
    if(this.marked){
      this.ptask = false;
      this.addTaskForm.get('start_Date').disable();
      this.addTaskForm.get('parentTask').disable();
      this.addTaskForm.get('end_Date').disable();
      this.addTaskForm.get('user_ID').disable();
      this.options = Object.assign({}, this.options, {disabled: this.marked});
    }else{
      this.ptask = true;
      this.addTaskForm.get('start_Date').enable();
      this.addTaskForm.get('parentTask').enable();
      this.addTaskForm.get('end_Date').enable();
      this.addTaskForm.get('user_ID').enable();
      this.options = Object.assign({}, this.options, {disabled: this.marked});
    }

    return;
  }

  onSubmit() {
    this.error={isError:false,errorMessage:''};
    var stdt = this.addTaskForm.get('start_Date').value;
    var eddt = this.addTaskForm.get('end_Date').value;
    var sdw = this.validateDates(stdt, eddt);
    if(sdw){
      this.addCourse(this.parentIdVal);
      this.submitted = true;
      this.ptask = true;
    }
  }

  addTask(){
  let task = {"taskname":this.task, "priority":this.value,
  "parenttask":this.parentTask, 
  "startdate":this.start_Date, "enddate": this.end_Date};
  //alert(task);
  }

  setdates(){
      const today =  new Date();
      let ss = this.datepipe.transform(today, 'yyyy-MM-dd');
      this.addTaskForm.controls['start_Date'].setValue(ss);
      const tomorrow =  new Date(today.setDate(today.getDate() + 1));
      let ss1 = this.datepipe.transform(tomorrow, 'yyyy-MM-dd');
      this.addTaskForm.controls['end_Date'].setValue(ss1);
  }

  addCourse(parentId:number){
    this.showmsgVal = false;
    //const task1 = {this.taskname, this.value, this.parentTask, this.startDate, this.endDate};
    //console.log(title, summary)
   /*his.tskService.addCourse({task, priority, parentTask, start_Date, end_Date, end_Task})
    .then(data => {
      this.message = data.message
      console.log(data)
      // this.courses = data
    })*/
    //alert(this.addTaskForm.value.parentTask);
    for(let gg of this.tasks){
      for(var i in gg){
        if(i === 'parent_Task' && this.addTaskForm.value.parentTask === gg[i]){
          this.ptid=gg['parent_ID'];
        }
      }
    }
    console.log(this.addTaskForm.value);
    if(!this.marked){
      alert('sdfa'+this.ptid);
      var pttid = this.addTaskForm.get('parentTask').value;
      this.tskService.addCourse(this.addTaskForm.value, pttid)
      .then(data => {
        this.message = data.message
        console.log(data)
        this.showmsgVal = true;
      })
    }else{
      this.tskService.addPT(this.addTaskForm.value, this.ptid)
      .then(data => {
        this.message = data.message
        console.log(data)
        this.showmsgVal = true;
      })
    }
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

  /*add(): void {
    this.editTask = undefined;
    let task = {"task":this.taskname, "priority":this.value, "start_Date":this.startDate, "end_Date": this.endDate};
    const headers = new HttpHeaders().set("Content-Type",  "application/json");
    const proxyurl = "https://cors-anywhere.herokuapp.com/";
    const url = "http://localhost:8088/7/tasks";
    this.http.post(proxyurl + url, task, {headers}).subscribe(
      (val) => {
        console.log("PUT successfull");
      },
      response => {
        console.log("PUT error", response);
      },
      () => {
        console.log("PUT Observable is complete");
      }
    );
  }*/


  reset(){
    this.showmsgVal = false;
    this.marked = false;
    this.theCheckbox = false;
    this.submitted = false;
    this.ptask = false;
    this.setdates();
  }

  value: number = 0;
  options: Options = {
    floor: 0,
    ceil: 30
  };

}
