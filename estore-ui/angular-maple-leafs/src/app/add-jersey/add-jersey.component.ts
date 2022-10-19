import { Component, OnInit } from '@angular/core';
import { Jersey } from '../jersey';
import { JerseyService } from '../jersey.service';

@Component({
  selector: 'app-add-jersey',
  templateUrl: './add-jersey.component.html',
  styleUrls: ['./add-jersey.component.css']
})
export class AddJerseyComponent implements OnInit {
  owner: boolean = true; //TODO need to do login
  
  name: string = "";
  number: number = 0;
  cost: number = 0;
  size: number = 0;
  isHome: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  createJersey(): void {
    this.jerseyService.createJerseys()
  }

}
