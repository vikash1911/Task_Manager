import { Injectable } from '@angular/core';
import { AddUserInt } from  './add-user-int';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import { User } from "../module/user";
import { HttpHeaders } from '@angular/common/http';
import { HttpClient, HttpParams } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })
};


@Injectable({
  providedIn: 'root'
})
export class AddUserService implements AddUserInt{

  constructor(private http1: HttpClient, private http: Http) { }

  fetchUserAll(): Observable<any>{
    return this.http.get('http://localhost:8088/getAllUsers')
         .pipe(
            map(res => res.json())
         )
  }

  addUser(user:User): Promise<any>{
    return this.http.post('http://localhost:8088/addUser', user)
    .toPromise()
    .then(res=> res.json())
  }

  deleteHero (id: number): Observable<{}> {
    const url = 'http://localhost:8088/deleteUserById/'+id; // DELETE api/heroes/42
    return this.http1.delete(url, httpOptions)
      .pipe(
        
      );
  }

  updateUser(user:User, id:number){
    return this.http.post('http://localhost:8088/updateUser/'+id, user)
    .toPromise()
    .then(res=> res.json())
  }

}
