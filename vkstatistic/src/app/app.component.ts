import { Component,OnInit, ElementRef,AfterViewInit} from '@angular/core';
import { UserServiceService } from './user-service.service';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit,AfterViewInit {
//   response:any;
// userName:string="";

  title = 'VK Statistic';
  // constructor(private userServiceService: UserServiceService)
  // {
  condition: boolean=false;
  // }
  constructor(private http: HttpClient, private router:Router,private elementRef: ElementRef)
  {
    
  }
  ngAfterViewInit(){
  //  this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = '';
 }
   ngOnInit(){
    var uuid=localStorage.getItem('UUID');
    if(uuid!=null)
    {
    this.condition=false;
    }  
    else{
      this.condition=true; 
    }
  //   this.userServiceService.getData().subscribe((response)=>{
  //         this.response=response;
  //         console.log(this.response);
  //       })
  //     };

   }
  // search()
  // {
  //   const headers = new HttpHeaders();
  //   headers.append('Access-Control-Allow-Headers', 'X-Requested-With,content-type');
  //   headers.append('Access-Control-Allow-Methods', 'GET');
  //   headers.append('Access-Control-Allow-Origin', '*');
  //   headers.append('Access-Control-Allow-Credentials', 'true');
  
  //   this.http.get('https://api.github.com/users/'+this.userName, {headers: headers}).subscribe((response)=>{
  //     this.response=response;
  //     console.log(this.response);
  //   })
  // }
 
  // search2()
  // {
  //   const headers = new HttpHeaders();
  //   headers.append('Access-Control-Allow-Headers', 'X-Requested-With,content-type');
  //   headers.append('Access-Control-Allow-Methods', 'GET');
  //   headers.append('Access-Control-Allow-Origin', '*');

  //   this.http.get('https://api-my-users.herokuapp.com/api-users/getAll', {headers: headers}).subscribe((response)=>{
  //     this.response=response;
  //     console.log(this.response);
  //   })
  // }
}
