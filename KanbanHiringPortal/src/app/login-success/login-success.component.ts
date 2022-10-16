import { Component, OnInit } from '@angular/core';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import {User} from '../user';
import { KanbanServiceService } from '../kanban-service.service';
import {MatDialog,MatDialogConfig,MatDialogRef} from '@angular/material/dialog';
import { ModalComponent } from '../modal/modal.component';

import { Status } from '../status';
import { MatRipple } from '@angular/material/core';


@Component({
  selector: 'app-login-success',
  templateUrl: './login-success.component.html',
  styleUrls: ['./login-success.component.css']
})
export class LoginSuccessComponent{


  // ModalVariables------

  dialogConfig=new MatDialogConfig();
  modalDialog:MatDialogRef<ModalComponent,any>|undefined;


  // -----------------

  objData:any;

  constructor(private _service:KanbanServiceService,public matDialog:MatDialog) { }

  data:any[]=[];
  backlog:any[] = [];
  inProgress:any[]=[];
  inReview:any[]=[];
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
      console.log(event.container.element.nativeElement.id);
      var dumpContainer:String=event.container.element.nativeElement.id;
      if(dumpContainer==="cdk-drop-list-0"){
        console.log("this is container 1")
        var id:string=this.backlog[event.currentIndex].candidateId;
        console.log(id);
        var container=new Status(0,id);
        this._service.updateStatus(container).subscribe(
          response=>{console.log("response received for data update")
          },
          error=>{console.log("error occurred in data updatation"+ error)
          }
        )
      } 
      else if(dumpContainer==="cdk-drop-list-1"){
        console.log("this is container 2")
        var id:string=this.inProgress[event.currentIndex].candidateId;
        console.log(id);
        var container=new Status(1,id)
        this._service.updateStatus(container).subscribe(
          response=>{console.log("response received for data update")
          },
          error=>{console.log("error occurred in data updatation"+ error)
          }
        )
      }

      else if(dumpContainer==="cdk-drop-list-2"){
        console.log("this is container 3")
        console.log(event.container)
        var id:string=this.inReview[event.currentIndex].candidateId;
        console.log(id);
        var container=new Status(2,id)
        this._service.updateStatus(container).subscribe(
          response=>{console.log("response received for data update")
          },
          error=>{console.log("error occurred in data updatation"+ error)
          }
        )
      }
      else if(dumpContainer==="cdk-drop-list-3"){
        var id:string=this.done[event.currentIndex].candidateId;
        console.log(id);
        var container=new Status(3,id)
        this._service.updateStatus(container).subscribe(
          response=>{console.log("response received for data update")
          },
          error=>{console.log("error occurred in data updatation"+ error)
          }
        )
      }
      
    }
    
  }

  // openModal() {
  //   this.modalRef = this.modalService.open(ModalComponent, {
  //     modalClass: 'modal-dialog-centered'
  //   })
  // }

  display(event:any){
    console.log("The div is hit")
    // console.log(event.currentIndex)
    // console.log(event.data)
    // console.log(event)
    console.log(event);
    this.dialogConfig.id="modalId";
    this.dialogConfig.height="500px";
    this.dialogConfig.width="650px";
    this._service.sendObject(event);
    
    this.modalDialog=this.matDialog.open(ModalComponent,this.dialogConfig)
  }
  ngOnInit(): void {
    console.log("Get Data Hit")
    this._service.getDataFromBackend(this.data).subscribe(
      response=>{
        console.log('response received')
        this.data=response;
        for (let i=0;i<this.data.length;i++){
          if(this.data[i].status===0){
              this.backlog.push(this.data[i])
          }
          else if(this.data[i].status===1){
            this.inProgress.push(this.data[i])
          }
          else if(this.data[i].status===2){
            this.inReview.push(this.data[i])
          }
          else if(this.data[i].status===3){
            this.done.push(this.data[i])
          }
        
      }

      },
      error=>{
        console.log("exception occurred")
      }
    )
    
  }
}
