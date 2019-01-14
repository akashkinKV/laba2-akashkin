import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserInfo } from '../userInfo';
import {Router} from '@angular/router';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class RegistrationComponent implements OnInit {

  BASE_API_URL:any='https://api-my-gateway.herokuapp.com/api-gateway/';

  response:any;
 
  user:UserInfo;
 
  //constructor(private router:Router) { }
  condition: boolean=false;
  constructor(private http:HttpClient,
    private router:Router,config: NgbModalConfig, private modalService: NgbModal) {
    // customize default values of modals used by this component tree
    config.backdrop = 'static';
    config.keyboard = false;
  }


  
  ngOnInit() {
  
    // this.http.get(this.BASE_API_URL+'user.getAll').subscribe((response)=>{
    //   this.response=response;
    //   console.log(this.response);

    //   const user: UserInfo = { email:"12345",vk:"basta",password:"12345" } as UserInfo;
    //   this.http.post(this.BASE_API_URL+'create',user,{
    //     headers:new HttpHeaders(
    //       {
    //         'Content-Type':'application/json'
    //       }
    //     )
    //   }).subscribe((response)=>{
    //   this.response=response;
    //   console.log(this.response);
    // })
  //})

  

  }
  rotate()
  {
    this.router.navigate(['/login']);
  }
  registration(vk:string, email: string,password: string,content)
  {
    

    const user: UserInfo = {vk:vk, email:email,password:password } as UserInfo;

    this.http.post(this.BASE_API_URL+'user.create', user,{
          headers:new HttpHeaders(
            {
              'Content-Type':'application/json'
            }
          )}).subscribe(
            res =>
            { 
              console.log(res);
              this.modalService.open(content);
            },
            msg => {
         
              this.condition=true;  
             
            }
          );

      
          
  //         subscribe((response)=>{ 
           
  // this.user2=Object.assign(new UserInfo(), response);
  //     console.log(this.user2.vk);
  // localStorage.setItem('UUID',this.user2.uid);
  //   })
  }

}
