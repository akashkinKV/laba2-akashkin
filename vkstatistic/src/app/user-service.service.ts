import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient){ }
      
  getData(){
      return this.http.get('https://api-my-users.herokuapp.com/api-users/getAll')
  }

}
