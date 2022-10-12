import { Injectable } from '@angular/core';
import {User} from './user';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import { getNumberOfCurrencyDigits } from '@angular/common';
import { AuthGuard } from './shared/auth.guard';




@Injectable({
  providedIn: 'root'
})
export class KanbanServiceService  {

  status="";

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

  public updateStatus(id:string,status:number){
    // return this._http.put<any>("http://localhost")
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
