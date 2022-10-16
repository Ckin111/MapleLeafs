import { Component, OnInit } from '@angular/core';
import { Jersey } from '../jersey';
import { JerseyService } from '../jersey.service';

@Component({
  selector: 'app-browse-jersey',
  templateUrl: './browse-jersey.component.html',
  styleUrls: ['./browse-jersey.component.css']
})
export class BrowseJerseyComponent implements OnInit {
  jerseys: Jersey[] = [];
  constructor(private jerseyService: JerseyService) { }

  ngOnInit(): void {
    this.getJerseys();
  }

  getJerseys(): void {
    this.jerseyService.getJerseys().subscribe(jerseys => this.jerseys = jerseys);
  }

}
