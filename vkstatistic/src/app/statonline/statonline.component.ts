import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';


@Component({
  selector: 'app-statonline',
  templateUrl: './statonline.component.html',
  styleUrls: ['./statonline.component.css']
})
export class StatonlineComponent implements OnInit {

 URL_Base:any="https://api-my-gateway.herokuapp.com/api-gateway/statOnline.getAll" 

  displayedColumns: string[] = ['Дата и время', 'В онлайне', 'Мобилный'];
  
  dataSource = null;
  page = 1;
  pagecount = 0;
  constructor(private http:HttpClient) { }
response:any;
  ngOnInit() {
    var uuid=localStorage.getItem('UUID');
    let params = new HttpParams().set("uuid",uuid);
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    this.http.get(this.URL_Base,{ headers: headers, params: params }).subscribe(
        res =>
        { console.log(Object.assign( res));
         this.dataSource=res;
         this.response=res;
         
        },
        msg => {
         
          console.log(msg);
         
        }
      );
  }

}


