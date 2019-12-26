import { Task } from "../module/task";
import { ParentTask } from "../module/parenttask";
import { Observable } from "rxjs";

export interface AddTaskInt {
    fetchAllTask(): Observable<any>;
    fetchprojectAll(): Observable<any>;
    fetchUserAll(): Observable<any>;
    addCourse(task:Task, prtId:number): Promise<any>;
    addPT(task:Task, prtId:number): Promise<any>;
    updatetsk(task:Task, Id:number, pId:number): Promise<any>;
    fetchpt():Observable<any>;
    disabletask(task:Task, tsk:number): Promise<any>;
}
