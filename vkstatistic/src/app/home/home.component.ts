import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Stat } from '../stat';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  BASE_API_URL:any='https://api-my-gateway.herokuapp.com/api-gateway/statistic.getAll';

  constructor(private http:HttpClient) { }

stat:Stat;
response:any;
  ngOnInit() {
    let params = new HttpParams().set("uuid",localStorage.getItem('UUID')).set("vk", localStorage.getItem('vk'))
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    this.http.get(this.BASE_API_URL,{ headers: headers, params: params }).subscribe(
        res =>
        { console.log(Object.assign( res));
         this.response=res;
         console.log(this.response);
         console.log(this.response[0]);
        
         
        },
        msg => {
         
          console.log(msg);
         
        }
      );
  }

}
