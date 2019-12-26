import { Injectable } from '@angular/core';
import { AddTaskInt } from  './add-task-int';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import { Task } from "../module/task";
import { ParentTask } from "../module/parenttask";

const baseUrl: string = 'http://localhost:8088/getAllTasks';

@Injectable({
  providedIn: 'root'
})
export class AddTaskService implements AddTaskInt{

  constructor(private http: Http) { }

  ptval: Observable<ParentTask[]>;

  fetchAllTask(): Observable<any>{
    return this.http.get(baseUrl)
         .pipe(
            map(res => res.json())
         )
  }

  fetchpt():Observable<any>{
    return this.http.get('http://localhost:8088/getAllParentTask')
    .pipe(
       map(res => res.json())
    )
  }

  fetchprojectAll():Observable<any>{
    return this.http.get('http://localhost:8088/getAllProjects')
    .pipe(
       map(res => res.json())
    )
  }

  fetchUserAll():Observable<any>{
    return this.http.get('http://localhost:8088/getAllUsers')
    .pipe(
       map(res => res.json())
    )
  }

  addCourse(task:Task, prtId:number): Promise<any>{
    
    return this.http.post('http://localhost:8088/'+prtId+'/task', task)
    .toPromise()
    .then(res=> res.json())
  }

  addPT(task:Task, prtId:number): Promise<any>{ 

    return this.http.post('http://localhost:8088/createParentTask', task)
    .toPromise()
    .then(res=> res.json())
  }

  updatetsk(task:Task, Id:number, pId:number): Promise<any>{
    
    return this.http.post('http://localhost:8088/updateTask/'+Id+"/"+pId, task)
    .toPromise()
    .then(res=> res.json())
  }

  disabletask(task:Task, tsk:number){
    return this.http.post('http://localhost:8088/disableTask/'+tsk, task)
    .toPromise()
    .then(res=> res.json())
  }


}
