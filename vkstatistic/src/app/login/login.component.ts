
import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders,HttpParams } from '@angular/common/http';
import { UserInfo } from '../userInfo';
import {Router} from '@angular/router';
import {ActivatedRoute} from '@angular/router';
import { Validators } from '@angular/forms';
import { OauthToken } from '../OauthToken';
import { AccessToken } from '../AccessToken';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

    BASE_API_URL:any='https://api-my-gateway.herokuapp.com/api-gateway/';
    BASE_OAUTH_URL:any='https://oauth-site.herokuapp.com/authorize';

  response:any;
  param1: string;
  param2: string;
  user:UserInfo;
  token:AccessToken;
  constructor(private http:HttpClient,
    private router:Router,private route: ActivatedRoute) { 
      this.route.queryParams.subscribe(params => {
        this.param1 = params['code'];

    });
    }
  //constructor(private router:Router) { }
  condition: boolean=false;
  condition2: boolean=false;  
  condition3: boolean=false;  
  ngOnInit() {
    var myurl=window.location.href;
    var url = new URL(myurl);
var autorize_code = url.searchParams.get("code");
console.log("code"+autorize_code);
if(autorize_code!=null)
{
  var grant_type_oauth='authorization_code';
  const oauth20: OauthToken = { client_id:"9aa3ae11759ae257e2f29484a32820845ae59763",
  client_secret:"3997673d7814bbbcde139fe181e7fba723beb70a4e6e49363230ff78051f40d1",
grant_type:grant_type_oauth,code: autorize_code,redirect_uri:"http://localhost:8080/login"} as OauthToken;


this.http.post(this.BASE_API_URL+'oauth20/tokens', oauth20,{
    headers:new HttpHeaders(
      {
        'Content-Type':'application/json'
      }
    )}).subscribe(
      res =>
      { console.log(res);
        this.token=Object.assign(new AccessToken(), res);
        localStorage.setItem('access_token',this.token.access_token);
        localStorage.setItem('refresh_token',this.token.refresh_token);
        localStorage.setItem('UUID',this.token.userId);
    if(this.token.userId!=null)
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
oauth()
{    
 
// window.location.href=this.BASE_OAUTH_URL+'?response_type=code&client_id=9aa3ae11759ae257e2f29484a32820845ae59763&redirect_uri=http://localhost:8080/login&scope=basic';
 window.location.href=this.BASE_OAUTH_URL.concat("?response_type=code")
 .concat("&client_id=9aa3ae11759ae257e2f29484a32820845ae59763")
 .concat("&redirect_uri=http://localhost:8080/login")
 .concat("&scope=basic");
 
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

  var grant_type_oauth='authorization_code';
  const oauth20: OauthToken = { client_id:localStorage.getItem('client_id'),
  client_secret:localStorage.getItem('client_secret'),
grant_type:grant_type_oauth,code: this.param1,redirect_uri:localStorage.getItem('redirect_uri')} as OauthToken;


this.http.post(this.BASE_API_URL+'oauth20/tokens', oauth20,{
    headers:new HttpHeaders(
      {
        'Content-Type':'application/json'
      }
    )}).subscribe(
      res =>
      { console.log(res);
        this.token=Object.assign(new AccessToken(), res);
        localStorage.setItem('access_token',this.token.access_token);
        localStorage.setItem('refresh_token',this.token.refresh_token);
       
       // this.router.navigate(['home']);
      //  window.location.reload();
        

      },
      msg => {
       
        this.condition=true;  
       
      }
    );


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
