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


  BASE_API_URL:any='https://api-my-gateway.herokuapp.com/api-gateway/statAll.get';

  constructor(private http:HttpClient) { }

stat:Stat;
response:any;
condition: boolean=false;
condition2: boolean=false;
  ngOnInit() {
    localStorage.setItem('vk',"basta")
  let params = new HttpParams().set("uuid",localStorage.getItem('UUID')).set("vk", localStorage.getItem('vk'))
  //   let headers = new HttpHeaders();
  //  // headers.append('Content-Type', 'application/json');
  //   headers.append('Authorization', 'Bearerlox ');
  //   this.http.get(this.BASE_API_URL,{ headers: headers, params: params }).subscribe(
  //       res =>
  //       { console.log(Object.assign( res));
  //        this.response=res;
  //        console.log(this.response);
  //        console.log(this.response[0]);
        
         
  //       },
  //       msg => {
         
  //         console.log(msg);
         
  //       }
  //     );
  // }
  

  this.http.get(this.BASE_API_URL, { headers: new HttpHeaders({
    'Authorization': localStorage.getItem('access_token'),
    'Content-Type': 'application/json',
  }), params: params}).subscribe (res => {

    console.log(Object.assign( res));
       this.response=res;
        console.log(this.response);
       console.log(this.response[0]);
        if(this.response.uid!=null) this.condition=true;
        if(this.response.first_name!=null) this.condition2=true;
   // this.items = JSON.parse(data._body).people;
    }, error => {
     console.log("Error with Data");
    });

}
}
