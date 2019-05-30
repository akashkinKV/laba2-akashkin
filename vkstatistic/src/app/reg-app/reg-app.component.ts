
import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserInfo } from '../userInfo';
import { OauthApp } from '../OauthApp';
import { OauthToken } from '../OauthToken';
import {Router} from '@angular/router';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-reg-app',
  templateUrl: './reg-app.component.html',
  styleUrls: ['./reg-app.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class RegAppComponent implements OnInit {

  BASE_API_URL:any='https://api-my-gateway.herokuapp.com/api-gateway/';

  response:any;
 public client:string;
 public secret:string;
  user:UserInfo;
 oauth:OauthApp;
 oauthToken:OauthToken;
  //constructor(private router:Router) { }
  condition: boolean=false;
  condition2: boolean=false;
  condition3: boolean=false;
  constructor(private http:HttpClient,
    private router:Router,config: NgbModalConfig, private modalService: NgbModal) {
    // customize default values of modals used by this component tree
    config.backdrop = 'static';
    config.keyboard = false;
  }


  
  ngOnInit() {
   // window.location.href=this.BASE_API_URL+'/oauth20/authorize?response_type=code&client_id=';
  
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
    //this.router.navigate(this.BASE_API_URL);
    window.location.href=this.BASE_API_URL+'oauth20/authorize?response_type=code&client_id='+this.client+'&redirect_uri=http://localhost:8080/login';
  }
  registration(name:string, description: string,scope: string,redirect_uri:string,content)
  {
 //   window.location.href=this.BASE_API_URL+'/oauth20/authorize?response_type=code&client_id='+this.client+'&redirect_uri='+this.oauthToken.redirect_uri;
  
if(name==null || description==null || scope==null  || redirect_uri==null )
{
 
  this.condition2=true;  
}
else{
  this.condition2=false;  


    const oauth: OauthApp = {name:name, description:description,scope:scope,redirect_uri:redirect_uri } as OauthApp;
  
    this.http.post(this.BASE_API_URL+'oauth20/applications', oauth,{
          headers:new HttpHeaders(
            {
              'Content-Type':'application/json'
            }
          )}).subscribe(
            res =>
            { 
              console.log(res);
              this.oauthToken=Object.assign(new OauthToken(), res);
             localStorage.setItem('client_id',this.oauthToken.client_id);
             localStorage.setItem('client_secret',this.oauthToken.client_secret);
             localStorage.setItem('redirect_uri',oauth.redirect_uri);
         this.client=this.oauthToken.client_id;
         this.secret=this.oauthToken.client_secret;
              this.condition=false;
              this.modalService.open(content);
            },
            msg => {
         
              this.condition=true;  
             
            }
          );
          }
          
          }
          
  //         subscribe((response)=>{ 
           
  // this.user2=Object.assign(new UserInfo(), response);
  //     console.log(this.user2.vk);
  // localStorage.setItem('UUID',this.user2.uid);
  //   })
  }


