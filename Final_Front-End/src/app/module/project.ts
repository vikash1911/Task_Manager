export class Project{

    constructor(public pid: number,
        public project: string, 
        public pro_start_date: Date,
        public pro_end_date: Date,
        public pro_priority: number,
        public numOfTasks: number,
        public pro_user_id: number){

    }
    
}