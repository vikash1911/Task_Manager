import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';

const baseUrl: string = 'http://localhost:8088/getAllTasks';

@Injectable({
  providedIn: 'root'
})
export class ViewTaskService {

  constructor(private http: Http) { }

  //taskVal:Task = null;

  fetchAllTask(): Observable<any>{
    return this.http.get(baseUrl)
         .pipe(
            map(res => res.json())
         )
  }

}
