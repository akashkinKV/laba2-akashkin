import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { UserInfo } from '../userInfo';
import {Router} from '@angular/router';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { OauthToken } from '../OauthToken';
import { AccessToken } from '../AccessToken';

@Component({
  selector: 'app-setting',
  templateUrl: './setting.component.html',
  styleUrls: ['./setting.component.css']
})
export class SettingComponent implements OnInit {

  condition: boolean=false;

  BASE_API_URL:any='https://api-my-gateway.herokuapp.com/api-gateway/';
  public model: any;
  token:AccessToken;
  constructor(private http:HttpClient,
    private router:Router,config: NgbModalConfig, private modalService: NgbModal) {
    // customize default values of modals used by this component tree
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit() {
  }

  rotate()
  { 
    var uuid=localStorage.getItem('UUID');
    let params = new HttpParams().set("uuid",uuid);
   
    this.http.delete(this.BASE_API_URL+'user.delete',{ headers: new HttpHeaders({
      'Authorization': localStorage.getItem('access_token'),
      'Content-Type': 'application/json',
    }), params: params}).subscribe(
            res =>
            { 
              console.log(res);
      
              localStorage.clear();
 

              this.router.navigate(['']);
              window.location.reload();

            },
            err => 
            {console.log(err.status)
            if(err.status==401)
            {
              if(localStorage.getItem('refresh_token')!=null)
              {
                var grant_type_oauth='refresh_token';
                const oauth20: OauthToken = { client_id:localStorage.getItem('client_id'),
                client_secret:localStorage.getItem('client_secret'),
              grant_type:grant_type_oauth,refresh_token:localStorage.getItem('refresh_token')} as OauthToken;
              
              console.log(oauth20);
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
                      this.router.navigate(['home']);
                     
                     // 
                      window.location.reload();
                      
              
                    },
                    msg => {
                     
                     
                     
                    }
                  );
    
              }
    
            }
            }
          );


  }
  rotate2()
  { 
    var uuid=localStorage.getItem('UUID');

   
    const user: UserInfo = {vk:this.model, uid:uuid } as UserInfo;

    this.http.put(this.BASE_API_URL+'user.updateVK', user,{
          headers:new HttpHeaders(
            { 'Authorization': localStorage.getItem('access_token'),
              'Content-Type':'application/json'
            }
          )}).subscribe(
            res =>
            { 
              console.log(res);
              localStorage.setItem("vk",this.model);
              this.router.navigate(['/home']);
             
 

            
              window.location.reload();

            },
            err => 
            {console.log(err.status)
              if(err.status==503)
          this.condition=true;
            if(err.status==401)
            {
              if(localStorage.getItem('refresh_token')!=null)
              {
                var grant_type_oauth='refresh_token';
                const oauth20: OauthToken = { client_id:localStorage.getItem('client_id'),
                client_secret:localStorage.getItem('client_secret'),
              grant_type:grant_type_oauth,refresh_token:localStorage.getItem('refresh_token')} as OauthToken;
              
              console.log(oauth20);
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
                   
                     
                     // this.router.navigate(['home']);
                      window.location.reload();
                      
              
                    },
                    msg => {
                     
                     
                     
                    }
                  );
    
              }
    
            }
            }
          );


  }

  delete(content)
  {

    this.modalService.open(content);
  }
    delete2(content2)
  {

    this.modalService.open(content2);
  }
}
