import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';


@Component({
  selector: 'app-statonline',
  templateUrl: './statonline.component.html',
  styleUrls: ['./statonline.component.css']
})
export class StatonlineComponent implements OnInit {

 URL_Base:any="https://api-my-gateway.herokuapp.com/api-gateway/statOnline.getAll" 

  displayedColumns: string[] = ['date', 'online', 'mobile'];
  
  dataSource = null;
  constructor(private http:HttpClient) { }

  ngOnInit() {
    var uuid=localStorage.getItem('UUID');
    let params = new HttpParams().set("uuid",uuid);
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    this.http.get(this.URL_Base,{ headers: headers, params: params }).subscribe(
        res =>
        { console.log(Object.assign( res));
         this.dataSource=res;
          
         
        },
        msg => {
         
          console.log(msg);
         
        }
      );
  }

}
export interface PeriodicElement {
  date: Date;
  online: boolean;
  mobile: boolean;
  
}

