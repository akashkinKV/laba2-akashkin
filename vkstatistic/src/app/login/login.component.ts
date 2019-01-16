import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserInfo } from '../userInfo';
import {Router} from '@angular/router';
import { Validators } from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

    BASE_API_URL:any='https://api-my-gateway.herokuapp.com/api-gateway/';

  response:any;
 
  user:UserInfo;
  constructor(private http:HttpClient,
    private router:Router) { }
  //constructor(private router:Router) { }
  condition: boolean=false;
  condition2: boolean=false;  
  condition3: boolean=false;  
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

  login(email: string,password: string)
  {
    if(email==null || password==null  )
{
 
  this.condition2=true;  
}
else{
  this.condition2=false;  

 
 var regexp = new RegExp('\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b');
 var test = regexp.test(email);
 if(test==true){
  this.condition3=false;  
    const user: UserInfo = { email:email,password:password } as UserInfo;

    this.http.post(this.BASE_API_URL+'user.login', user,{
          headers:new HttpHeaders(
            {
              'Content-Type':'application/json'
            }
          )}).subscribe(
            res =>
            { console.log(res);
              this.user=Object.assign(new UserInfo(), res);
              localStorage.setItem('UUID',this.user.uid);
              localStorage.setItem('vk',this.user.vk);
              this.condition=false;
              var uuid=localStorage.getItem('UUID');
              if(uuid!=null)
              {
              this.router.navigate(['home']);
              window.location.reload();
              }

            },
            msg => {
             
              this.condition=true;  
             
            }
          );
          }
          else{
            this.condition3=true;  
          }
          }
      
          
  //         subscribe((response)=>{ 
           
  // this.user2=Object.assign(new UserInfo(), response);
  //     console.log(this.user2.vk);
  // localStorage.setItem('UUID',this.user2.uid);
  //   })
  }

  
}
