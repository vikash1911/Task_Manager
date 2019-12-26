import { Project } from "../module/project";
import { Observable } from "rxjs";

export interface AddProjectInt {
    fetchProjectAll(): Observable<any>;
    addProject(user:Project, deft:boolean ,id:number): Promise<any>;
    updateProject(user:Project, deft:boolean, id:number): Promise<any>;
    fetchUserAll(): Observable<any>;
}
