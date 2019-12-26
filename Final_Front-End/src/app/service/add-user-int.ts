import { User } from "../module/user";
import { Observable } from "rxjs";

export interface AddUserInt {
    fetchUserAll(): Observable<any>;
    addUser(user:User): Promise<any>;
    updateUser(user:User, id:number): Promise<any>;
    deleteHero (id: number): Observable<{}>;
}
