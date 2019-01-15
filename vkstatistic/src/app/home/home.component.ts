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


 URL_API_VK:string = "https://api.vk.com/method/users.get";
   VK_TOKEN:string = "5c5e70cfcf443268b00e8914fc9b752980d7c428691f9458d510eaa8f9ec1b7d16695aa764b516fc27a4f";


  constructor(private http:HttpClient) { }

stat:Stat;
response:any;
  ngOnInit() {
    let params = new HttpParams().set("user_ids","basta").set("fields", "counters").set("access_token",this.VK_TOKEN); 
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    this.http.get(this.URL_API_VK,{ headers: headers, params: params }).subscribe(
        res =>
        { console.log(Object.assign( res));
         this.response=res;
           this.stat=Object.assign( res);
           
          console.log(this.stat);
          console.log(this.stat[0]);
         
        },
        msg => {
         
          console.log(msg);
         
        }
      );
  }

}
