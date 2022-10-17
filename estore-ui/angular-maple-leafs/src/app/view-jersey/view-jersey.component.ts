import { Component, OnInit } from '@angular/core';
import { Jersey, Size } from '../jersey';
import { JerseyService } from '../jersey.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-view-jersey',
  templateUrl: './view-jersey.component.html',
  styleUrls: ['./view-jersey.component.css']
})

export class ViewJerseyComponent implements OnInit {
  sizeList: String[] = [Size[0], Size[1], Size[2], Size[3]];
  jersey: Jersey | undefined;
  owner: boolean | undefined;
  home: boolean | undefined;
  constructor(private route: ActivatedRoute, 
    private jerseyService: JerseyService, 
    private location: Location) { }

  ngOnInit(): void {
    this.getJersey();
    console.log("Help Me!");
  }

  getJersey(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    console.log(id);
    this.jerseyService.getJersey(id).subscribe(jersey => this.jersey = jersey);
    this.owner = true;
    this.home = this.jersey?.home;
  }

  back(): void {
    this.location.back();
  }

  

}
