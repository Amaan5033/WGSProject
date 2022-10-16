import { Injectable } from '@angular/core';
import {User} from './user';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import { getNumberOfCurrencyDigits } from '@angular/common';
import { AuthGuard } from './shared/auth.guard';
/*import {Status} from './status';*/




@Injectable({
  providedIn: 'root'
})
export class KanbanServiceService  {

  status="";

  object:any;
  constructor(private _http:HttpClient) { }

  public logInUserFromRemote(user:User):Observable<any>{
    return this._http.post<any>('http://localhost:8080/login',user)
  }

  public registerUserFromRemote(user:User):Observable<any>{
    return this._http.post<any>("http://localhost:8080/register",user)
  }

  public getDataFromBackend(data:any):Observable<any>{
    return this._http.post<any>("http://localhost:8080/getData",data);
  }

  // public updateStatus(status:Status){
  //   return this._http.put<any>("http://localhost:8080/updateData",status);
  // }

  public sendObject(obj:any){
    this.object=obj;
    console.log(obj.candidateId)
    

  }


  public giveDatatoModal(obj:any):Observable<any>{
    obj=this.object;
    console.log("giveDataTOModal activated")
    console.log(obj)
    return this._http.post<any>("http://localhost:8080/getObj",obj)
  }

  uploadingImage(imageFormData:any):Observable<any>{
    return this._http.post<any>("http://localhost:8080/upload/image/",imageFormData);
  }


  viewingImage(image:any){
    return this._http.get<any>("http://localhost:8080/get/image/info/"+image);
  }

  public statusReceived(status:string){
    if(status==="ok"){
      this.status="ok";
      return "ok";
    }
    else{
      console.log("notok hit")
      this.status=="notok"
      return "notok";
    }
  }

  public statusSend(value:String){
    if(this.status=="ok"){
      value="ok"
      return value;
    }
    else{
      value="notok"
      return value;
    }
  }
}
