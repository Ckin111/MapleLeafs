import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Jersey } from '../jersey';
import { JerseyService } from '../jersey.service';

import { Observable, Subject } from 'rxjs';

import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-search-jerseys',
  templateUrl: './search-jerseys.component.html',
  styleUrls: ['./search-jerseys.component.css']
})
export class SearchJerseysComponent implements OnInit, AfterViewInit {

  jerseys$!: Observable<Jersey[]>;
  private searchTerms = new Subject<string>();
  username: string = "";


  /**
   * when instantiate browse-jersey
   * @param jerseyService the service to talk to jersey API
   */
  constructor(private jerseyService: JerseyService, private route: ActivatedRoute) { }
  ngAfterViewInit(): void {
    this.search("J");
  }

  search(term: any): void {
    this.searchTerms.next(term.target.value);
  }

  ngOnInit(): void {
    this.jerseys$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.jerseyService.searchJerseys(term))
    );
    this.username = String(this.route.snapshot.paramMap.get('username'));
  }



  // idk what yall did but imma say just do a getalljerseys() because this is not worth ur time
  // if not, just add a load booelan and instead of using an async pipe just bring the jerseys in once all are downloaded
}
