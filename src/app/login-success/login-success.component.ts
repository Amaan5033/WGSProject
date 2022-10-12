import { Component, OnInit } from '@angular/core';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import {User} from '../user';
import { KanbanServiceService } from '../kanban-service.service';
import { Candidate } from '../candidate';


@Component({
  selector: 'app-login-success',
  templateUrl: './login-success.component.html',
  styleUrls: ['./login-success.component.css']
})
export class LoginSuccessComponent{

  constructor(private _service:KanbanServiceService) { }

  // data:any[]=[];


  todo:any[] = [];

  done:any[]=[];

  drop(event: CdkDragDrop<any[]>) {
    
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
      console.log(event.currentIndex);
      console.log(event.container.data)
    }
    
  }

  getData(){
  }

  ngOnInit(): void {
    console.log("Get Data Hit")
    this._service.getDataFromBackend(this.todo).subscribe(
      response=>{
        console.log('response received')
        this.todo=response;
      },
      error=>{
        console.log("exception occurred")
      }
    )
  }

}
