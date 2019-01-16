import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-statistic',
  templateUrl: './statistic.component.html',
  styleUrls: ['./statistic.component.css']
})
export class StatisticComponent implements OnInit {

  URL_Base:any="https://api-my-gateway.herokuapp.com/api-gateway/statistic.getAll" 

  displayedColumns: string[] = ['Дата_записи', 'Друзей', 'Сообществ', 'Подписчиков', 'Подписки', 'Фотоальбомов', 'Видеозаписей', 'Аудиозаписей', 'Заметок', 'Подарки'];
  
  dataSource = null;
  page = 1;
  pagecount = 0;
  constructor(private http:HttpClient) { }
response:any;
  ngOnInit() {
    let params = new HttpParams().set("uuid",localStorage.getItem('UUID')).set("vk", localStorage.getItem('vk'))
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
