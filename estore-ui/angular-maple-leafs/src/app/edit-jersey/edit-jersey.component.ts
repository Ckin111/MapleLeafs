import { Component, OnInit } from '@angular/core';
import { Jersey } from '../jersey';
import { JerseyService } from '../jersey.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
@Component({
  selector: 'app-edit-jersey',
  templateUrl: './edit-jersey.component.html',
  styleUrls: ['./edit-jersey.component.css']
})
export class EditJerseyComponent implements OnInit {
  jerseys: Jersey[] = [];

  jersey: Jersey = {
    id: -1,
    name: "",
    cost: NaN,
    size: NaN,
    home: false,
    number: NaN
  };
  owner: boolean = true; //TODO need to do login stuff

  constructor(private route: ActivatedRoute,
    private jerseyService: JerseyService,
    private location: Location,
    private router: Router) {}

  ngOnInit(): void {
    this.editJersey();
  }

  editJersey(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    console.log("Editing");
    this.jerseyService.getJersey(id).subscribe(jersey => this.jersey = jersey);
  }

  back(): void {
    this.location.back();
  }

  save(): void {
    this.jerseyService.editJersey(this.jersey).subscribe(jersey => {this.jerseys.push(jersey);});
    // this.router.navigateByUrl("view/" + this.jersey.id);
    this.back();
  }

}
