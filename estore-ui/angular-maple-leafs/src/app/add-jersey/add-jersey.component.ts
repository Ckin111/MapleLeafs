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
  jerseys: Jersey[] = [];
  name: string = "";
  number: number = NaN;
  cost: number = NaN;
  size: number = 0;
  isHome: boolean = false;

  sizes = [
    {siz: "Small", val: 0},
    {siz: "Medium", val: 1},
    {siz: "Large", val: 2},
    {siz: "XL", val: 3}
  ];

  jersey: Jersey = {
    id: 0,
    name: "",
    number: 0,
    cost: 0,
    size: 0,
    home: false
  }
  constructor(private jerseyService: JerseyService) { }

  ngOnInit(): void {
  }

  addJersey(): void {
    this.jersey.name = this.name;
    this.jersey.number = this.number;
    this.jersey.cost = this.cost;
    this.jersey.size = this.size;
    this.jersey.home = this.isHome;

    this.jerseyService.addJersey(this.jersey).subscribe(jersey => {this.jerseys.push(jersey);});
  }

}
