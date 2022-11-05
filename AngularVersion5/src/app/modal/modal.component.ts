import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { KanbanServiceService } from '../kanban-service.service';
import {HttpClient,HttpEventType} from '@angular/common/http';
import { Candidate } from '../candidate';
import { Comment } from '../comment';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { FormGroup,FormControl } from '@angular/forms';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {

  public userFile:any=File;
  formdata:any=FormGroup;
  constructor(private _service:KanbanServiceService,
    public dialogRef:MatDialogRef<ModalComponent>,
    private _http:HttpClient) { }

  candidateObj:any;
  commentObj:any;

  commentList:Comment[]=[];
  // Upload Image Parameter-------------

  uploadImage!:File;
  dbImage:any;
  postResponse:any;
  successResponse!:String;
  image:any;



  // --------------------------------------
  

            

  ngOnInit(): void {
    
    console.log("nginIt activated")
    this._service.giveDatatoModal(this.candidateObj).subscribe(
      response=>{console.log("i am give data");console.log(response);this.candidateObj=response},
      error=>{("error occurred")}
    );
    this._service.showAllComments(this.commentObj).subscribe(
      response=>{console.log("i am all comment");console.log("getting all the comments ok");this.commentList=response},
      error=>{console.log("getting all the comments are not ok")}
    );
    console.log("This is from modal component")

  }


  closeModal(){
    this.dialogRef.close();
  }

  onImageUpload(event:any){
    this.uploadImage=event.target.files[0];
  }
  
  imageUploadAction(){
    const imageFormData=new FormData();
    imageFormData.append('image',this.uploadImage,this.uploadImage.name);
    this._service.uploadingImage(imageFormData).subscribe(
      response=>{console.log("response received");this.postResponse=response;
                                                  this.successResponse=this.postResponse.body.message},
      error=>{("error occurred");this.successResponse="Image failed to upload"}
    );
  }

  viewImage(){
    this._service.viewingImage(this.image).subscribe(
      response=>{this.postResponse=response;this.dbImage='data:image/jpeg;base64,'+this.postResponse.image},
      error=>{}
    );
  }

  getComment(value:String){
    var commentObj=new Comment(this.candidateObj.candidateId,value);
    this._service.getAllComments(commentObj).subscribe(
      response=>{console.log("response recieved for comment section");this.commentList=response},
      error=>{console.log("error occurred in comment section response")}
    );
  }

  onSelectFile(event:any){
    const file=event.target.files[0];
    // console.log(file);
    this.userFile=file;
  }

  submitImage(formdata:FormGroup){
    const user=formdata.value;
    
    var formData=new FormData();
    formData.append('user',JSON.stringify(user));
    formData.append('file',this.userFile);
    formData.append('candidateId',this.candidateObj.candidateId);
    this._service.saveCandidateImage(formData).subscribe(
      response=>{console.log("response received for image upload")},
      error=>{console.log("error while uploading the image")}
    );
    console.log(formData)
  }
}
