import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserInfo } from '../userInfo';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
    BASE_API_URL:any='https://api-my-users.herokuapp.com/api-users/';
text:string="hello";
  response:any;
 
  user2:UserInfo;
  constructor(private http:HttpClient) { }

  ngOnInit() {
  
    this.http.get(this.BASE_API_URL+'getAll').subscribe((response)=>{
      this.response=response;
      console.log(this.response);

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
  })




  }

  login(email: string,password: string)
  {

    const user: UserInfo = { email:email,password:password } as UserInfo;

    this.http.post(this.BASE_API_URL+'login', user,{
          headers:new HttpHeaders(
            {
              'Content-Type':'application/json'
            }
          )}).subscribe(
            res => console.log(res),
            msg => {
              console.log(msg.status);
              console.log(msg.status);
            }
          );
          
          
  //         subscribe((response)=>{ 
           
  // this.user2=Object.assign(new UserInfo(), response);
  //     console.log(this.user2.vk);
  // localStorage.setItem('UUID',this.user2.uid);
  //   })
  }

  
}
