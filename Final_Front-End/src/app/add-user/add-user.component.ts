import { Component, OnInit, Inject } from '@angular/core';
import {Router} from '@angular/router';
import { from } from 'rxjs';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { Options } from 'ng5-slider';
import { Task } from '../module/task';
import { Http, Response, RequestOptions } from '@angular/http';
import {map} from 'rxjs/operators';
import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { catchError } from 'rxjs/operators';
import { AddUserService } from '../service/add-user.service';

class DataTablesResponse {
  data: any[];
  draw: number;
  recordsFiltered: number;
  recordsTotal: number;
}

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  dtOptions: DataTables.Settings = {};
  submitted = false;
  showmsgVal: boolean;
  users = [];
  headers: Headers;
  options: RequestOptions;
  update = false;
  uservall = 'Add';
  cret = ' Created ';

  constructor(private http: Http,private fb:FormBuilder, 
    @Inject('User') private usrService: AddUserService) { 
    }
  
    addUserForm = this.fb.group({
      uid:[''],
      first_name:['', Validators.required],
      last_name:['', Validators.required],
      employee_id: ['', Validators.required]
    })
  
    get f() { return this.addUserForm.controls; }

  ngOnInit() {
    this.showmsgVal = false;
    const that = this;
    this.viewusertable(); 
  }

  viewusertable(){
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 2,
      serverSide: true,
      processing: true,
      ajax: (dataTablesParameters: any, callback) => {
        this.http.get('http://localhost:8080/getAllUsers')
         .pipe(
            map(res => res.json())
         ).subscribe(data => {
          console.log(data)
          this.users = data;
          callback({
            recordsTotal: data.recordsTotal,
            recordsFiltered: data.recordsFiltered,
            data: []
          });
        });
      },
      columns: [{ data: 'first_name' }, { data: 'last_name' }, { data: 'employee_id' }]
    };
  }

  reset(){
    this.uservall = 'Add';
    this.cret = ' Created ';
    this.http.get('http://localhost:8080/getAllUsers')
         .pipe(
            map(res => res.json())
         ).subscribe(data => {
          console.log(data)
          this.users = data;
        });
  }

  rest(){
    this.showmsgVal = false;
    this.uservall = 'Add1';
    this.cret = ' Created ';
  }

  onSubmit(){
    if(!this.update){
      this.rest();
      this.usrService.addUser(this.addUserForm.value)
        .then(data => {
          console.log(data)
          this.showmsgVal = true;
          this.update = false;
          this.viewusertable();
          this.reset();
        })
    }else{
      this.rest();
      var id = this.addUserForm.get('uid').value;
      this.usrService.updateUser(this.addUserForm.value, id)
        .then(data => {
          console.log(data)
          this.viewusertable();
          this.reset();
          this.cret = ' Updated ';
          this.uservall = 'Update ';
          this.showmsgVal = true;
          this.update = false;
        })
    }  
  }

  editUser(value){
    this.rest();
    this.http.get('http://localhost:8080/getUserById/'+value)
         .pipe(
            map(res => res.json())
         ).subscribe(data => {
          console.log(data)
          this.addUserForm.controls['uid'].setValue(data.uid);
          this.addUserForm.controls['first_name'].setValue(data.first_name);
          this.addUserForm.controls['last_name'].setValue(data.last_name);
          this.addUserForm.controls['employee_id'].setValue(data.employee_id);
          this.showmsgVal = false;
          this.update = true;
          this.uservall = 'Update ';
          this.cret = ' Updated ';
        });
    //this.updateTaskForm.controls['project_ID'].setValue(gg1[i]);
  }

  delete(value){
    this.usrService.deleteHero(value).subscribe();
    this.viewusertable();
    this.reset();
    window.location.reload();
  }

}
