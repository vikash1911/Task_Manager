export class Task{

    constructor(public task: string, 
        public priority:number, 
        public parentTask: string,
        public start_Date: Date,  
        public end_Date: Date, 
        public end_Task:boolean,
        public project_ID: number,
        public user_ID: number){

    }
    
}