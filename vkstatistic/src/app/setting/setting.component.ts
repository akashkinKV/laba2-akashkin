import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { UserInfo } from '../userInfo';
import {Router} from '@angular/router';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-setting',
  templateUrl: './setting.component.html',
  styleUrls: ['./setting.component.css']
})
export class SettingComponent implements OnInit {

  BASE_API_URL:any='https://api-my-gateway.herokuapp.com/api-gateway/';
  public model: any;
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
   
    this.http.delete(this.BASE_API_URL+'user.delete',{params:params}).subscribe(
            res =>
            { 
              console.log(res);
      
              localStorage.clear();
 

              this.router.navigate(['']);
              window.location.reload();

            },
            msg => {
         
            
             
            }
          );


  }
  rotate2()
  { 
    var uuid=localStorage.getItem('UUID');

   
    const user: UserInfo = {vk:this.model, uid:uuid } as UserInfo;

    this.http.put(this.BASE_API_URL+'user.updateVK', user,{
          headers:new HttpHeaders(
            {
              'Content-Type':'application/json'
            }
          )}).subscribe(
            res =>
            { 
              console.log(res);
              this.router.navigate(['/home']);
              localStorage.setItem("vk",this.model);
 

            
              window.location.reload();

            },
            msg => {
         
            
             
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
