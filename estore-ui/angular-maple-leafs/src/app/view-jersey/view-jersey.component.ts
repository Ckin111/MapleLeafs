/**
 * view-jersey component. Get a jersey from an API and 
 * display it. This is also the component that has the interface
 * to buy, update, or delete jersey
 */

import { Component, OnInit } from '@angular/core';
import { Jersey, Size } from '../jersey';
import { JerseyService } from '../jersey.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { debounceTime } from 'rxjs';

@Component({
  selector: 'app-view-jersey',
  templateUrl: './view-jersey.component.html',
  styleUrls: ['./view-jersey.component.css']
})

export class ViewJerseyComponent implements OnInit {

  sizeList: String[] = [Size[0], Size[1], Size[2], Size[3]];
  jersey: Jersey | undefined;
  owner: boolean = true; //TODO need to do login stuff
  home: boolean | undefined;

  /**
   * When a view-jersey component is created
   * @param route to be able to read the route
   * @param jerseyService service that connects to jersey API
   * @param location to be able to change url routes
   */
  constructor(private route: ActivatedRoute, 
    private jerseyService: JerseyService, 
    private location: Location,
    private router: Router) { }

  ngOnInit(): void {
    this.getJersey();
  }

  /**
   * Gets specified jersey (id) from the jerseyService
   */
  getJersey(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    console.log(id);
    this.jerseyService.getJersey(id).subscribe(jersey => this.jersey = jersey);
    this.home = this.jersey?.home;
  }

  /**
   * Will go back to the previous page
   */
  back(): void {
    this.location.back();
  }

  /**
   * To delete the specified jersey
   */
  delete(): void {
    const id = this.jersey?.id;
    if(id != undefined) {
      if(confirm("Are you sure you want to delete " + this.jersey?.name +
       " " + this.jersey?.number + "?")) {
        console.log(id);
        this.jerseyService.deleteJersey(id).subscribe();
        this.router.navigate(['/browse']);
       }
    }
    
  }

}
