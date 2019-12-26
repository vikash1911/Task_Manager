import { Injectable } from '@angular/core';
import { AddProjectInt } from  './add-project-int';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import { Project } from "../module/project";
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class AddProjectService implements AddProjectInt{

  constructor(private http: Http, public datepipe: DatePipe) { }

  fetchProjectAll(): Observable<any>{
    return this.http.get('http://localhost:8080/getAllProjects')
         .pipe(
            map(res => res.json())
         )
  }

  addProject(project:Project, deflt:boolean, id:number): Promise<any>{
    if(!deflt){
    const today =  new Date();
      const tomorrow =  new Date(today.setDate(today.getDate() + 1));
      const nextday =  new Date(today.setDate(today.getDate() + 2));
      let ss = this.datepipe.transform(tomorrow, 'yyyy-MM-dd');
      let ss1 = this.datepipe.transform(nextday, 'yyyy-MM-dd');
      var Pro = new Project(id,project.project, tomorrow, nextday, project.pro_priority,0, project.pro_user_id);
    return this.http.post('http://localhost:8080/addProject', Pro)
    .toPromise()
    .then(res=> res.json())
    }
    else{
      return this.http.post('http://localhost:8080/addProject', project)
    .toPromise()
    .then(res=> res.json())
    }
  }

  updateProject(project:Project, deflt:boolean, id:number): Promise<any>{
    if(!deflt){
    const today =  new Date();
      const tomorrow =  new Date(today.setDate(today.getDate() + 1));
      const nextday =  new Date(today.setDate(today.getDate() + 2));
      let ss = this.datepipe.transform(tomorrow, 'yyyy-MM-dd');
      let ss1 = this.datepipe.transform(nextday, 'yyyy-MM-dd');
      var Pro = new Project(id, project.project, tomorrow, nextday, project.pro_priority,0, project.pro_user_id);
    return this.http.post('http://localhost:8080/updateProject/'+id, Pro)
    .toPromise()
    .then(res=> res.json())
    }
    else{
      return this.http.post('http://localhost:8080/updateProject/'+id, project)
    .toPromise()
    .then(res=> res.json())
    }
  }

  fetchUserAll():Observable<any>{
    return this.http.get('http://localhost:8080/getAllUsers')
    .pipe(
       map(res => res.json())
    )
  }

}
