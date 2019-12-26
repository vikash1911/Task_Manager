import { Component, OnInit, Inject } from '@angular/core';
import {Router} from '@angular/router';
import { from } from 'rxjs';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { Options } from 'ng5-slider';
import { Project } from '../module/project';
import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { catchError } from 'rxjs/operators';
import { AddProjectService } from '../service/add-project.service';
import { DatePipe } from '@angular/common';
import { Http, Response, RequestOptions } from '@angular/http';
import {map} from 'rxjs/operators';

class DataTablesResponse {
  data: any[];
  draw: number;
  recordsFiltered: number;
  recordsTotal: number;
}

@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.css']
})
export class AddProjectComponent implements OnInit {

  submitted = false;
  showmsgVal: boolean;
  users = [];
  marked = false
  deflt = false;
  error:any={isError:false,errorMessage:''};
  isValidDate:any;
  dtOptions: DataTables.Settings = {};
  projects = [];
  proList: Array<Project> = [];
  num: number;
  update = false;
  updt = 'Add';
  cret = ' Created ';

  constructor(private http: Http, private fb:FormBuilder, 
    @Inject('Project') private projectService: AddProjectService
    , public datepipe: DatePipe) { }

    addProjectForm = this.fb.group({
      pid:[''],
      project:['', Validators.required],
      theCheckbox:[''],
      pro_start_date: ['', Validators.required],
      pro_end_date: ['', Validators.required],
      pro_priority: ['', Validators.required],
      pro_user_id: ['', Validators.required]
    })
  
    get f() { return this.addProjectForm.controls; }

  ngOnInit() {
    this.showmsgVal = false;
    
    this.projectService.fetchUserAll()
  .subscribe(data => {
      console.log(data)
      this.users = data;
    });
    this.disabledates();
    this.viewprojecttable();
    //this.resetVal();
  }

  viewprojecttable(){
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 2,
      serverSide: true,
      processing: true,
      ajax: (dataTablesParameters: any, callback) => {
        this.http.get('http://localhost:8080/getAllProjects')
         .pipe(
            map(res => res.json())
         ).subscribe(data => {
          console.log(data)
          this.projects = data;
          //this.setnumm(data);
          callback({
            recordsTotal: 20,
            recordsFiltered: 10,
            data: []
          });
        });
      },
      columns: [{ data: 'project' }, { data: 'pro_start_date' }, 
      { data: 'pro_end_date' }, { data: 'pro_priority' }]
    };
  }

  setnumm(val:Array<Project>){
    for(let gg1 of val){
      var aa = 0;
      var bb = '';
      var cc: Date;
      var dd: Date;
      var ee = 0;
      var ff = 0;
      for(var i in gg1){
        if(i === 'pid'){
          //aa = this.getvalll(gg1[i]); 
        }
        if(i === 'project'){
         bb = gg1[i];
        }
        if(i === 'pro_start_date'){
         cc = gg1[i];
        }
        if(i === 'pro_end_date'){
          dd = gg1[i];
        }
        if(i === 'pro_priority'){
          ee = gg1[i];
        }
        if(i === 'pro_user_id'){
          ff = gg1[i];
        }
      }
      //alert(this.num);
      //setTimeout(()=>{
     // let ss = new Project(bb,cc,dd,ee,aa,ff);
      //this.proList.push(ss);
      //},10);
      console.log(this.proList);
    }
    this.projects = this.proList;
    console.log(this.projects);
  }

  getvalll(vall:string){
    var sw = 0;
    this.http.get('http://localhost:8080/getNumOfTasksById/'+vall)
    .pipe(
       map(res => res.json())
    ).subscribe(data => {
      //alert('zdc');
      sw = data;
   });
   alert(sw)
   return sw;
  }

  resetVal(){
    this.http.get('http://localhost:8080/getAllProjects')
         .pipe(
            map(res => res.json())
         ).subscribe(data => {
          console.log(data)
          this.projects = data;
          //this.updt = 'Add';
          //this.cret = ' Created ';
        });
  }

  toggleVisibility(e){
    this.marked= e.target.checked;
    if(!this.marked){
      this.disabledates();
    }else{
      this.addProjectForm.get('pro_start_date').enable();
      this.addProjectForm.get('pro_end_date').enable();
    }
    return;
  }

  disabledates(){
    const today =  new Date();
      const tomorrow =  new Date(today.setDate(today.getDate() + 1));
      const nextday =  new Date(today.setDate(today.getDate() + 2));
      let ss = this.datepipe.transform(tomorrow, 'yyyy-MM-dd');
      let ss1 = this.datepipe.transform(nextday, 'yyyy-MM-dd');
      this.addProjectForm.controls['pro_start_date'].setValue(ss);
      this.addProjectForm.controls['pro_end_date'].setValue(ss1);
      this.addProjectForm.get('pro_start_date').disable();
      this.addProjectForm.get('pro_end_date').disable();
  }

  onSubmit(){
    this.resethalf();
    if(!this.update){
      this.update = false;
      this.isValidDate = this.validateDates(this.addProjectForm.get('pro_start_date').value
      , this.addProjectForm.get('pro_end_date').value, this.marked);
      var id = this.addProjectForm.get('pid').value;
      if(this.isValidDate){
        this.projectService.addProject(this.addProjectForm.value, this.marked, id)
          .then(data => {
            console.log(data)
            this.showmsgVal = true;
          })
      }
      this.viewprojecttable();
      this.resetVal();
    }else{
      this.isValidDate = this.validateDates(this.addProjectForm.get('pro_start_date').value
      , this.addProjectForm.get('pro_end_date').value, this.marked);
      var id = this.addProjectForm.get('pid').value;
      alert(id);
      if(this.isValidDate){
        this.projectService.updateProject(this.addProjectForm.value, this.marked, id)
          .then(data => {
            console.log(data)
            //this.updt = 'Update ';
            this.cret = ' Updated ';
            this.showmsgVal = true;
            this.update = false;
          })
      }
      this.viewprojecttable();
      this.resetVal();
    }
  }

  updatePro(value){
    this.resethalf();
    this.update = true;
    this.addProjectForm.get('pro_start_date').enable();
    this.addProjectForm.get('pro_end_date').enable();
    alert(value);
    this.http.get('http://localhost:8080/getProjectById/'+value)
         .pipe(
            map(res => res.json())
         ).subscribe(data => {
          console.log(data)
          this.addProjectForm.controls['project'].setValue(data.project);
          let ss = this.datepipe.transform(data.pro_start_date, 'yyyy-MM-dd');
          this.addProjectForm.controls['pro_start_date'].setValue(ss);
          let ss1 = this.datepipe.transform(data.pro_end_date, 'yyyy-MM-dd');
          this.addProjectForm.controls['pro_end_date'].setValue(ss1);
          this.addProjectForm.controls['pro_priority'].setValue(data.pro_priority);
          this.addProjectForm.controls['pro_user_id'].setValue(data.pro_user_id);
          this.addProjectForm.controls['pid'].setValue(value);
          this.showmsgVal = false;
          this.updt = 'Update ';
          this.cret = ' Updated ';
        });
  }

  numOfTask(id:number){
    var dd = 0;
    /*this.http.get('http://localhost:8080/getNumOfTasksById/'+id)
         .pipe(
            map(res => res.json())
         ).subscribe(data => {
          console.log(data)
          alert(data);
          dd = data;
        });*/
    return dd;
  }

  proCompleted(eDate: string){
    const today =  new Date();
    let ss = this.datepipe.transform(today, 'yyyy-MM-dd');
    let ss1 = this.datepipe.transform(eDate, 'yyyy-MM-dd');
    if(ss < ss1){
      return 'No';
    }else{
      return 'Yes';
    }
  }

  resetDateErrorMsg(){
    this.error={isError:false,errorMessage:''};
      this.isValidDate = true;
  }

  validateDates(sDate: string, eDate: string, mrkd:boolean){
    this.isValidDate = true;
    if((sDate == null || eDate ==null) && mrkd){
      this.error={isError:true,errorMessage:'Start date and end date are required.'};
      this.isValidDate = false;
    }

    if((sDate != null && eDate !=null) && (eDate) < (sDate) && mrkd){
      this.error={isError:true,errorMessage:'End date should be grater then start date.'};
      this.isValidDate = false;
    }
    return this.isValidDate;
  }

  resethalf(){
    this.resetDateErrorMsg();
    //this.disabledates();
    this.showmsgVal = false;
    //this.updt = 'Add ';
    //this.cret = ' Created ';
  }

  reset(){
    this.resetDateErrorMsg();
    this.disabledates();
    this.showmsgVal = false;
    this.updt = 'Add ';
    this.cret = ' Created ';
  }

  value: number = 0;
  options: Options = {
    floor: 0,
    ceil: 30
  };

}
