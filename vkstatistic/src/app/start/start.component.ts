import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {
  images = [1, 2, 3].map(() => `https://picsum.photos/900/500?random&t=${Math.random()}`);
  constructor( private router:Router) { }

  ngOnInit() {

 
    var uuid=localStorage.getItem('UUID');
    if(uuid!=null)
    {
    this.router.navigate(['home']);
    }

  }

}
